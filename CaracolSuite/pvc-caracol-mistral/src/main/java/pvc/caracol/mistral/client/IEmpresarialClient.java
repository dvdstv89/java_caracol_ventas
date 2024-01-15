package pvc.caracol.mistral.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pvc.caracol.common.reponse.ApiWebResponse;

@FeignClient(name = "pvc-caracol-empresarial")
public interface IEmpresarialClient {
    @PostMapping("/api/v1/centro-gestion/database-mistral/{idCentroGestion}")
    ApiWebResponse findBaseDatosMistralByCodeCentroGestion(@PathVariable Integer idCentroGestion);

    @PostMapping("/api/v1/centro-gestion/tienda/{idTienda}")
    ApiWebResponse findTiendaByIdTienda(@PathVariable String idTienda);
}
