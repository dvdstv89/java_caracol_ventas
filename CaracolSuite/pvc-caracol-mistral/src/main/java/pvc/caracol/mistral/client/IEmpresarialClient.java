package pvc.caracol.mistral.client;


import pvc.caracol.mistral.model.DataBaseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pvc-caracol-empresarial")
public interface IEmpresarialClient {
    @GetMapping("/api/v1/empresarial/database-mistral/{code}")
    DataBaseInfo findBaseDatosMistralByCodeCentroGestion(@PathVariable String code);

}
