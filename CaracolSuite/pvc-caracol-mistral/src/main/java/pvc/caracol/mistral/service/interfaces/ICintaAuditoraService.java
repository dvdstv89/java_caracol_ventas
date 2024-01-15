package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundCausedException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;

public interface ICintaAuditoraService {
    ApiWebResponse getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException, FeignClientException, NotFoundCausedException;
}
