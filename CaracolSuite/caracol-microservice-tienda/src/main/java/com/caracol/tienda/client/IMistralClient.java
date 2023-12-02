package com.caracol.tienda.client;

import com.caracol.tienda.dtos.DataBaseMistralDto;
import com.caracol.tienda.http.request.CajasActivasDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "caracol-msvc-mistral")
public interface IMistralClient {
    @PostMapping("/api/v1/mistral/cajas-activas-centro-gestion/{centroGestion}")
    CajasActivasDto getCajasActivasCentroGestion(@PathVariable String centroGestion);
}
