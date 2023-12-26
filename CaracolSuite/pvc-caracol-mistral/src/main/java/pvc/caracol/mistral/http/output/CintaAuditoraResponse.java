package pvc.caracol.mistral.http.output;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CintaAuditoraResponse {
    private List<CintaAuditoraDto> cintaAuditora;
}
