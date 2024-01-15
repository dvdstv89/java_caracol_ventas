package pvc.caracol.cinad.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pvc.caracol.cinad.dtos.CintaAuditoraProcesadaDto;
import pvc.caracol.cinad.http.CintaAuditoraDto;
import pvc.caracol.cinad.messages.NameCaseTest;
import pvc.caracol.cinad.services.ICintaAuditoraService;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.messages.MessageText;
import pvc.caracol.common.reponse.ApiWebResponse;

@RestController
@Tag(name = "CINAD", description = "Análisis de Cintas Auditoras. **Uso exclusivo del microsistema Tiendas**")
@RequestMapping("/api/v1/cinad")
public class CinadController extends BaseController {
    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public CinadController(ICintaAuditoraService cintaAuditoraService, Logger logger) {
        super(logger);
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @Operation(
            summary = "Analizar una pk auditora",
            description = "Realiza el análisis de una pk auditora especifica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = NameCaseTest.CINTA_AUDITORA_OK_200),
            @ApiResponse(responseCode = "400", description = NameCaseTest.CINTA_AUDITORA_ERROR_400),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500)
    })
    @PostMapping("analizar-pk")
    public ResponseEntity<CintaAuditoraProcesadaDto> analizarCintaAuditora(@RequestBody byte[] fichero) throws Exception {
        ApiWebResponse response = cintaAuditoraService.analizarCintaAuditora(fichero);
        return handleApiResponseToObject(response, MessageText.ENDPOINT_NAME_LOAD_MEDICATION);
    }
}
