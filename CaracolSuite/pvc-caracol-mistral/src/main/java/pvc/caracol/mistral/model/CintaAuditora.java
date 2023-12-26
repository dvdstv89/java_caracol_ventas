package pvc.caracol.mistral.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CintaAuditora {
    private String codigoAlmacen;
    private String codigoRed;
    private String idCaja;
    private Date fechaCreacion;
    private Date fechaProcesado;
    private byte[] fichero;
}
