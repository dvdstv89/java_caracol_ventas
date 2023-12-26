package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;

public interface ICintaAuditoraService {
    ApiResponse getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException;
}
