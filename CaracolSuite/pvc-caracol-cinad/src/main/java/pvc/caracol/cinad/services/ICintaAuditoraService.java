package pvc.caracol.cinad.services;

import pvc.caracol.cinad.http.CintaAuditoraDto;
import pvc.caracol.common.reponse.ApiResponse;

import java.io.IOException;

public interface ICintaAuditoraService {
    ApiResponse analizarCintaAuditora(CintaAuditoraDto cintaAuditoraDto) throws Exception;
}
