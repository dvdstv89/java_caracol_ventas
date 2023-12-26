package pvc.caracol.cinad.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pvc.caracol.cinad.services.ICintaAuditoraService;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.reponse.ApiResponse;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/cinad")
public class CinadController extends BaseController {
    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public CinadController(ICintaAuditoraService cintaAuditoraService, Logger logger) {
        super(logger);
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @PostMapping("analizar-cinta")
    public ResponseEntity<ApiResponse> analizarCintaAuditora(@RequestBody byte[] fichero) throws Exception {
        return ResponseEntity.ok(cintaAuditoraService.analizarCintaAuditora(fichero));
    }
}
