package pvc.caracol.cinad.dtos;

import lombok.Data;
import pvc.caracol.common.enums.FormaPago;

@Data
public class PagoDto {
    private double pagado;
    private FormaPago formaPago;
}
