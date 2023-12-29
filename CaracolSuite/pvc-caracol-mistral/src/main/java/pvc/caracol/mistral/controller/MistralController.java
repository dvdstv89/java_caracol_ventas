package pvc.caracol.mistral.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.mistral.dtos.CajaDto;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.http.output.CintaAuditoraDto;
import pvc.caracol.mistral.service.interfaces.ICajaService;
import pvc.caracol.mistral.service.interfaces.ICintaAuditoraService;
import  pvc.caracol.mistral.messages.MessageText;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mistral")
public class MistralController extends BaseController {
    private final ICajaService cajaService;
    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public MistralController(ICajaService cajaService, ICintaAuditoraService cintaAuditoraService, Logger logger) {
        super(logger);
        this.cajaService = cajaService;
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @PostMapping("cajas-activas-centro-gestion/{centroGestion}")
    public ResponseEntity<List<CajaDto>> getCajasActivasByCentroGestion(@PathVariable String centroGestion) throws NotFoundException {
        WebResponse response = cajaService.getCajasActivasByCentroGestion(centroGestion);
        return handleApiResponseToObject(response, MessageText.ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_CENTRO_GESTION);
    }

    @PostMapping("cintas-auditoras")
    public ResponseEntity<List<CintaAuditoraDto>> getCintasAuditoras(@RequestBody CajaRegistradoraDto cajaRegistradora) throws NotFoundException {
        WebResponse response = cintaAuditoraService.getCintaAuditora(cajaRegistradora);
        return handleApiResponseToObjectList(response, MessageText.ENDPOINT_CINTAS_AUDITORAS_BY_CAJA_REGGISTERADORA);
    }
}
