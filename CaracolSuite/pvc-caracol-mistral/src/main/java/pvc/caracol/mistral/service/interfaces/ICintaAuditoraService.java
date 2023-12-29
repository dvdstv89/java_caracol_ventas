package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;

public interface ICintaAuditoraService {
    WebResponse getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException;
}
