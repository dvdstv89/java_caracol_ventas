package pvc.caracol.tienda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;

@FeignClient(name = "pvc-caracol-mistral")
public interface IMistralClient {
    @PostMapping("/api/v1/mistral/cajas-activas-centro-gestion/{idCentroGestion}")
    ApiWebResponse getCajasActivasByIdCentroGestion(@PathVariable Integer idCentroGestion);

    @PostMapping("/api/v1/mistral/cajas-activas-tienda/{codigoTienda}")
    ApiWebResponse getCajasActivasByTienda(@PathVariable String codigoTienda);

    @PostMapping("/api/v1/mistral/cintas-auditoras")
    ApiWebResponse getCintasAuditoras(@RequestBody CajaRegistradoraDto cajaRegistradoraDto);
}
