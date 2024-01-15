package pvc.caracol.cinad.services;

import pvc.caracol.common.reponse.ApiWebResponse;

public interface ICintaAuditoraService {
    ApiWebResponse analizarCintaAuditora(byte[] fichero) throws Exception;
}
