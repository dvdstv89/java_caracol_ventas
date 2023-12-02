package com.caracol.tienda.controller;

import com.caracol.tienda.service.ITiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tienda")
public class TiendaController {

    private final ITiendaService service;

    @Autowired
    public TiendaController(ITiendaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo tienda");
    }

    @GetMapping("cajas-activas/{code}")
    public ResponseEntity<?> getCajasActivas(@PathVariable String code) {
        return ResponseEntity.ok(service.findCajasActivasByCodeCentroGestion(code));
    }
}
