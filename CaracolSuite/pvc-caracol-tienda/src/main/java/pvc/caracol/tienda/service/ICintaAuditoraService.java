package pvc.caracol.tienda.service;


import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.tienda.http.input.CintaAuditoraDto;
import pvc.caracol.tienda.http.input.FicheroCintaAuditoraRequest;
import pvc.caracol.tienda.http.output.CintaAuditoraResponse;

import java.io.IOException;
import java.util.List;

public interface ICintaAuditoraService {

    ApiResponse getCintasAuditoras(@RequestBody CintaAuditoraResponse cintaAuditoraRequest) throws IOException, FeignClientException;;

    ApiResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException;
}
