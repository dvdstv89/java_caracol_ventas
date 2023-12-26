package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CintaAuditoraDto {
    private String codigoAlmacen;
    private String codigoRed;
    private String idCaja;
    private LocalDate fechaCreacion;
    private LocalDate fechaProcesado;
    private byte[] fichero;
}
