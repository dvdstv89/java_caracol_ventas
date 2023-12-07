package com.system.mistral.controller;

import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.http.output.CajasActivasResponse;
import com.system.mistral.http.output.CintaAuditoraResponse;
import com.system.mistral.http.output.FicheroCintaAuditoraResponse;
import com.system.mistral.service.ICajaService;
import com.system.mistral.service.ICintaAuditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/mistral")
public class MistralController {
    private final ICajaService cajaService;
    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public MistralController(ICajaService cajaService, ICintaAuditoraService cintaAuditoraService) {
        this.cajaService = cajaService;
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo mistral");
    }

    @GetMapping("cajas-activas")
    public ResponseEntity<?> getCajasActivas() {
        return ResponseEntity.ok(cajaService.getCajasActivas());
    }

    @PostMapping("cajas-activas-centro-gestion/{centroGestion}")
    public ResponseEntity<CajasActivasResponse> getCajasActivasCentroGestion(@PathVariable String centroGestion) {
        return ResponseEntity.ok(cajaService.getCajasActivasCentroGestion(centroGestion));
    }

    @PostMapping("cintas-auditoras")
    public ResponseEntity<FicheroCintaAuditoraResponse> getCintasAuditoras(@RequestBody CintaAuditoraRequest cintaAuditoraRequest) throws IOException {
        CintaAuditoraResponse cintaAuditoraResponse = cintaAuditoraService.getCintaAuditora(cintaAuditoraRequest);
        return ResponseEntity.ok(cintaAuditoraService.procesarCintasAuditoras(cintaAuditoraResponse.getCintaAuditora()));
    }
}
