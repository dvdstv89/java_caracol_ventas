package com.caracol.mistral.controller;

import com.caracol.mistral.http.output.CajasActivasDto;
import com.caracol.mistral.model.DataBaseInfo;
import com.caracol.mistral.service.ICajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
