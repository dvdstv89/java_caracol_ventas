package pvc.caracol.tienda.http.input;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FicheroCintaAuditoraRequest {
    private List<FicheroCintaAuditoraDto> ficheroCintaAuditora;
}
