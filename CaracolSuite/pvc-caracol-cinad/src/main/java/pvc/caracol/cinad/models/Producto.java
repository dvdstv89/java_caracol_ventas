package pvc.caracol.cinad.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {
    private String code;
    private String name;
    private double precioUnitario;
    public boolean getIsInsumo() {
        return precioUnitario == 0;
    }
}
