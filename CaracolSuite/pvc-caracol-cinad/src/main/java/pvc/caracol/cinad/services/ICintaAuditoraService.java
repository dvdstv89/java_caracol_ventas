package pvc.caracol.cinad.services;

import pvc.caracol.cinad.http.CintaAuditoraDto;
import pvc.caracol.common.reponse.WebResponse;

public interface ICintaAuditoraService {
    WebResponse analizarCintaAuditora(CintaAuditoraDto cintaAuditoraDto) throws Exception;
}
