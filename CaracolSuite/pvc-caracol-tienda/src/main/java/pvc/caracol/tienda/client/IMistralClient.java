package pvc.caracol.tienda.client;

import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.tienda.http.input.CintaAuditoraDto;
import pvc.caracol.tienda.http.output.CintaAuditoraResponse;

import java.util.List;

@FeignClient(name = "pvc-caracol-mistral")
public interface IMistralClient {
    @PostMapping("/api/v1/mistral/cajas-activas-centro-gestion/{centroGestion}")
    ApiResponse getCajasActivasCentroGestion(@PathVariable String centroGestion);

    @PostMapping("/api/v1/mistral/cintas-auditoras")
    List<CintaAuditoraDto> getCintasAuditoras(@RequestBody CintaAuditoraResponse cintaAuditoraRequest) throws FeignException;
}
