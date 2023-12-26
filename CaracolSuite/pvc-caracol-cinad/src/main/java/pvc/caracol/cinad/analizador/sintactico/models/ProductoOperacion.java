package pvc.caracol.cinad.analizador.sintactico.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;
import pvc.caracol.cinad.models.Producto;
import pvc.caracol.cinad.repositories.ProductRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

@Data
@Getter
@NoArgsConstructor
public abstract class ProductoOperacion {
    protected Producto producto;
    protected double cantidad;

    public ProductoOperacion(String codigo, String nombre) {
        try {
            String[] productNameSplited = nombre.split(CharType.SPACE.getCharacterAsString());
            this.cantidad = Double.parseDouble(productNameSplited[0].trim());

            Producto producto = ProductRepository.getInstance().getProducto(codigo);
            this.producto = (producto != null)
                    ? producto
                    : crearProducto(codigo, productNameSplited);
        } catch (Exception e) {
            String error = e.toString();
        }
    }

    private Producto crearProducto(String codigo, String[] productoParts) {
        double costo = Double.parseDouble(productoParts[productoParts.length - 1]);
        double precioUnitario = Math.round((costo / cantidad) * 100.0) / 100.0;
        String productName = Arrays.stream(productoParts, 1, productoParts.length - 1)
                .filter(text -> !text.isEmpty())
                .collect(Collectors.joining(CharType.SPACE.getCharacterAsString()));
        Producto producto = new Producto(codigo, productName, precioUnitario);
        ProductRepository.getInstance().addProducto(producto);
        return producto;
    }

    public void addCantidad(double cantidad) {
        this.cantidad += cantidad;
    }

    public double getSaldo() {
        return Math.round((cantidad * producto.getPrecioUnitario()) * 100.0) / 100.0;
    }
}
