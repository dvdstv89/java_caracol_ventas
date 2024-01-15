package pvc.caracol.tienda.http.output;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CajaRegistradoraDto {
    private String idCaja;
    private String idTienda;
    private String codigoAlmacen;
    private String codigoRed;
    private Integer idCentroGestion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
