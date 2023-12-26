package pvc.caracol.mistral.http.input;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CajaRegistradoraDto {
    private Integer idCaja;
    private String codigoRed;
    private String codigoCentroGestion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
