package pvc.caracol.mistral.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CintaAuditora {
    private String codigoAlmacen;
    private String codigoRed;
    private String idCaja;
    private LocalDate fechaCreacion;
    private LocalDate fechaProcesado;
    private byte[] fichero;
}
