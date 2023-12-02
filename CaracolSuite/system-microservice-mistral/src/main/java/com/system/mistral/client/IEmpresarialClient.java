package com.system.mistral.client;


import com.system.mistral.model.DataBaseInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "caracol-msvc-organizacion-empresarial")
public interface IEmpresarialClient {
    @GetMapping("/api/v1/centro-gestion/database-mistral/{code}")
    DataBaseInfo findBaseDatosMistralByCodeCentroGestion(@PathVariable String code);

}
