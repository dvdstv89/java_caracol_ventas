package pvc.caracol.mistral.repository.interfaces;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.model.CintaAuditora;

import java.util.List;

public interface ICintaAuditoraRepository {
    List<CintaAuditora> getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException, FeignClientException;
}
