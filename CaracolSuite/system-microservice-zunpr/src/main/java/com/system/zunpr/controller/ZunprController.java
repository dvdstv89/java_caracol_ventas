package com.system.zunpr.controller;

import com.system.zunpr.service.IEmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zunpr")
public class ZunprController {

    private final IEmpleadoService service;

    @Autowired
    public ZunprController(IEmpleadoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo zunPr");
    }

    @GetMapping("empleados-activos")
    public ResponseEntity<?> getEmpleadosActivos() {
        return ResponseEntity.ok(service.getEmpleadosActivos());
    }
}
