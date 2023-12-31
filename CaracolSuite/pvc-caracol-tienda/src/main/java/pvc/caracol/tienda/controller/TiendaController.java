package pvc.caracol.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
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

    @GetMapping("cajas-activas/{code}")
    public ResponseEntity<WebResponse> getCajasActivas(@PathVariable String code) throws FeignClientException, NotFoundException {
        return ResponseEntity.ok(tiendaService.findCajasActivasByCodeCentroGestion(code));
    }

    @PostMapping("analizar-cintas-auditoras-by-caja")
    public ResponseEntity<WebResponse> analizarCintasAuditorasByCajaRegistradora(@RequestBody CajaRegistradoraDto cintaAuditoraRequest) throws IOException, FeignClientException {
        return ResponseEntity.ok(cintaAuditoraService.getCintasAuditorasByCaja(cintaAuditoraRequest));
    }
}
