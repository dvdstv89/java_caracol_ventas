package pvc.caracol.cinad.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pvc.caracol.cinad.dtos.CintaAuditoraProcesadaDto;
import pvc.caracol.cinad.http.CintaAuditoraDto;
import pvc.caracol.cinad.models.CintaAuditoraElectronica;
import pvc.caracol.cinad.services.ICintaAuditoraService;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.messages.MessageText;
import pvc.caracol.common.reponse.ApiResponse;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<CintaAuditoraProcesadaDto> analizarCintaAuditora(@RequestBody CintaAuditoraDto cintaAuditoraDto) throws Exception {
        ApiResponse response = cintaAuditoraService.analizarCintaAuditora(cintaAuditoraDto);
        return handleApiResponseToObject(response, MessageText.ENDPOINT_NAME_LOAD_MEDICATION);
    }
}
