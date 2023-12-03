package com.caracol.tienda.client;

import com.caracol.tienda.http.request.CajasActivasResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "system-msvc-mistral")
public interface IMistralClient {
    @PostMapping("/api/v1/mistral/cajas-activas-centro-gestion/{centroGestion}")
    CajasActivasResponse getCajasActivasCentroGestion(@PathVariable String centroGestion);
}
