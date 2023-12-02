package com.system.mistral.controller;

import com.system.mistral.http.output.CajasActivasDto;
import com.system.mistral.service.ICajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mistral")
public class MistralController {
    private final ICajaService service;

    @Autowired
    public MistralController(ICajaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo mistral");
    }

    @GetMapping("cajas-activas")
    public ResponseEntity<?> getCajasActivas() {
        return ResponseEntity.ok(service.getCajasActivas());
    }

    @PostMapping("cajas-activas-centro-gestion/{centroGestion}")
    public ResponseEntity<CajasActivasDto> getCajasActivasCentroGestion(@PathVariable String centroGestion) {
        return ResponseEntity.ok(service.getCajasActivasCentroGestion(centroGestion));
    }
}
