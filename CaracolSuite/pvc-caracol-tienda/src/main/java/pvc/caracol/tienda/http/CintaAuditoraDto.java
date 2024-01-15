package pvc.caracol.tienda.http;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CintaAuditoraDto {
    private String idTienda;
    private String idCaja;
    private String codigoAlmacen;
    private String codigoRed;
    private LocalDate fechaCreacion;
    private LocalDate fechaProcesado;
    private byte[] fichero;
}
