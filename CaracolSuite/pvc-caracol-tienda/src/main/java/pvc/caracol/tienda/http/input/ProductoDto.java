package pvc.caracol.tienda.http.input;

import lombok.Data;

@Data
public class ProductoDto {
    private String code;
    private String name;
    private double precioUnitario;
    private boolean isInsumo;
    private double cantidad;
    private double saldo;
}