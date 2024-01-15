package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CintaAuditoraProcesadaDto {
    private String idTienda;
    private String idCaja;
    private String codigoAlmacen;
    private String codigoRed;
    private String name;
    private Long byteSize;
    private LocalDateTime fechaCreacion;
    private Double paperSizeCentimetros;
    private Double paperSizeMetros;
    private Integer countLines;
    private String nombrePuntoVenta;
    private LocalDateTime fechaHaladoVenta;
    private LocalDateTime fechaAnalisis;
    private List<DiaOperacionDto> diaOperacions;
}
