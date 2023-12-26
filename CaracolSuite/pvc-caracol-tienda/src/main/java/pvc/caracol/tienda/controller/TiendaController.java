package pvc.caracol.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;
import pvc.caracol.tienda.service.ICintaAuditoraService;
import pvc.caracol.tienda.service.ITiendaService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/tienda")
public class TiendaController {

    private final ITiendaService tiendaService;
    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public TiendaController(ITiendaService tiendaService, ICintaAuditoraService cintaAuditoraService) {
        this.tiendaService = tiendaService;
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo tienda");
    }

    @GetMapping("cajas-activas/{code}")
    public ResponseEntity<ApiResponse> getCajasActivas(@PathVariable String code) {
        return ResponseEntity.ok(tiendaService.findCajasActivasByCodeCentroGestion(code));
    }

    @PostMapping("cintas-auditoras")
    public ResponseEntity<ApiResponse> getCintasAuditoras(@RequestBody CajaRegistradoraDto cintaAuditoraRequest) throws IOException, FeignClientException {
        return ResponseEntity.ok(cintaAuditoraService.getCintasAuditorasByCaja(cintaAuditoraRequest));
    }
}
