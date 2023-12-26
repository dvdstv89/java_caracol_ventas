package pvc.caracol.mistral.repository.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.CintaAuditoraRequest;
import pvc.caracol.mistral.model.CintaAuditora;

import java.util.List;

public interface ICintaAuditoraRepository {
    List<CintaAuditora> getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) throws NotFoundException;
}
