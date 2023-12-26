package pvc.caracol.tienda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.input.CintaAuditoraProcesadaDto;

import java.io.IOException;

@FeignClient(name = "pvc-caracol-cinad")
public interface ICinadlClient {
    @PostMapping("/api/v1/cinad/analizar-cinta")
    CintaAuditoraProcesadaDto analizarCintaAuditora(CintaAuditoraDto cintaAuditoraDto) throws IOException;
}