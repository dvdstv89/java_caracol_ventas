package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.mistral.http.input.CintaAuditoraRequest;
import pvc.caracol.mistral.http.output.CintaAuditoraDto;

import java.util.List;

public interface ICintaAuditoraService {
    ApiResponse getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) throws NotFoundException;
}
