package pvc.caracol.tienda.http.output;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TiendaDto {
    private String codigoTienda;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
