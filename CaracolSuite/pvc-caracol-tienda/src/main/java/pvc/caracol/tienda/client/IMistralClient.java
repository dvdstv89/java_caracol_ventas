package pvc.caracol.tienda.client;

import feign.FeignException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;

import java.util.List;

@FeignClient(name = "pvc-caracol-mistral")
public interface IMistralClient {
    @PostMapping("/api/v1/mistral/cajas-activas-centro-gestion/{centroGestion}")
    WebResponse getCajasActivasByCentroGestion(@PathVariable String centroGestion);

    @PostMapping("/api/v1/mistral/cintas-auditoras")
    List<CintaAuditoraDto> getCintasAuditoras(@RequestBody CajaRegistradoraDto cajaRegistradoraDto) throws FeignException;
}
