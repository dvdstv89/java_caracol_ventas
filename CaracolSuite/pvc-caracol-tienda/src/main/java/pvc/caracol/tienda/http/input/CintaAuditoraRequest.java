package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.util.List;

@Data
public class CintaAuditoraRequest {
    private List<CintaAuditoraDto> cintaAuditora;
}
