package pvc.caracol.mistral.http.output;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CintaAuditoraDto {
    private String idCaja;
    private String codigoAlmacen;
    private String codigoRed;
    private LocalDate fechaCreacion;
    private LocalDate fechaProcesado;
    private byte[] fichero;
}
