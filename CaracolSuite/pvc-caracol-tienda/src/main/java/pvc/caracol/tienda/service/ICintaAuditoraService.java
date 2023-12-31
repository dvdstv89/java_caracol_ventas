package pvc.caracol.tienda.service;


import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;

import java.io.IOException;
import java.util.List;

public interface ICintaAuditoraService {

    WebResponse getCintasAuditorasByCaja(@RequestBody CajaRegistradoraDto cintaAuditoraRequest) throws IOException, FeignClientException, NotFoundException;

    ;

    WebResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException;
}
