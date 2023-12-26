package pvc.caracol.cinad.repositories;

import pvc.caracol.cinad.models.Producto;

import java.util.ArrayList;
import java.util.List;


public class ProductRepository {
    private static ProductRepository instance;
    private static List<Producto> productos;


    private ProductRepository() {
        productos = new ArrayList<>();
    }

    public static synchronized ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    public void addProducto(Producto producto) {
        Producto productoExistent = getProducto(producto.getCode());
        if (productoExistent == null) {
            productos.add(producto);
        }
    }

    public Producto getProducto(String code) {
        try {
            return productos.stream()
                    .filter(producto -> producto.getCode().equals(code))
                    .findFirst().orElse(null);
        }
        catch (Exception e){
            return  null;
        }
    }
}
