package pvc.caracol.tienda.service;


import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;

import java.io.IOException;
import java.util.List;

public interface ICintaAuditoraService {

    ApiResponse getCintasAuditorasByCaja(@RequestBody CajaRegistradoraDto cintaAuditoraRequest) throws IOException, FeignClientException;

    ;

    ApiResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException;
}