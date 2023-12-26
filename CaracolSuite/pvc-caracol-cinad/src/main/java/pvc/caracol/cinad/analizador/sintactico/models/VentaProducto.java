package pvc.caracol.cinad.analizador.sintactico.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VentaProducto extends ProductoOperacion implements Cloneable {
    public VentaProducto(String codigo, String nombre) {
        super(codigo, nombre);
    }

    @Override
    public VentaProducto clone() {
        try {
            return (VentaProducto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
