package pvc.caracol.tienda.http.output;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CintaAuditoraResponse {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String codigoCentroGestion;
    private String codigoRed;
    private Integer idCajaRegistradora;
}
