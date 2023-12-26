package pvc.caracol.tienda.http.input;

import lombok.Data;
import pvc.caracol.common.enums.FormaPago;

@Data
public class PagoDto {
    private double pagado;
    private FormaPago formaPago;
}
