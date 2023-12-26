package pvc.caracol.mistral.http.input;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CajaRegistradoraDto {
    private Integer idCaja;
    private String codigoRed;
    private String codigoCentroGestion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
