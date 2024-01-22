package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProcesarCintasTiendasDto {
    private String codigoTienda;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
