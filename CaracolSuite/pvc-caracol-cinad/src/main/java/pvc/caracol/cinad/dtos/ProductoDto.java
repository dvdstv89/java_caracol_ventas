package pvc.caracol.cinad.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDto {
    private String code;
    private String name;
    private double precioUnitario;
    private boolean isInsumo;
    private double cantidad;
    private double saldo;
}