package pvc.caracol.mistral.http.input;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CintaAuditoraRequest {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String codigoCentroGestion;
    private String codigoRed;
    private Integer idCajaRegistradora;
}
