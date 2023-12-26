package pvc.caracol.cinad.services;

import pvc.caracol.common.reponse.ApiResponse;

import java.io.IOException;

public interface ICintaAuditoraService {
    ApiResponse analizarCintaAuditora(byte[] fichero) throws Exception;
}
