package pvc.caracol.tienda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import pvc.caracol.common.reponse.ApiResponse;

import java.io.IOException;

@FeignClient(name = "pvc-caracol-cinad")
public interface ICinadlClient {
    @PostMapping("/api/v1/cinad/analizar-cinta")
    ApiResponse analizarCintaAuditora(byte[] fichero) throws IOException;
}
