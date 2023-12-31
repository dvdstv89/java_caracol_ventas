package pvc.caracol.mistral.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundCausedException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.mistral.dtos.CajaDto;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.http.output.CintaAuditoraDto;
import pvc.caracol.mistral.messages.MessageText;
import pvc.caracol.mistral.service.interfaces.ICajaService;
import pvc.caracol.mistral.service.interfaces.ICintaAuditoraService;

import java.util.List;

@RestController
@Tag(name = "Mistral", description = "Consultas a la base de datos de mistral")
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

    @Operation(
            summary = "Obtener las cajas registradoras",
            description = "Busca las cajas registradoras activas pertenecientes a un centro de gestion dado. Se obtiene el string de conexion del centro de gestion y luego" +
                    " se buscan todas sus cajas registradoras con estado activo = true.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = NameCaseTest.CAJAS_OK_200),
            @ApiResponse(responseCode = "404", description = NameCaseTest.CAJAS_NOT_FOUND_404),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
            @ApiResponse(responseCode = "503", description = NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503)
    })
    @PostMapping("cajas-activas-centro-gestion/{centroGestion}")
    public ResponseEntity<List<CajaDto>> getCajasActivasByCentroGestion(@PathVariable String centroGestion) throws NotFoundException, FeignClientException {
        WebResponse response = cajaService.getCajasActivasByCentroGestion(centroGestion);
        return handleApiResponseToObject(response, MessageText.ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_CENTRO_GESTION);
    }

    @Operation(
            summary = "Obtener las cintas auditoras",
            description = "Busca las cintas auditoras de una caja registradoras especifica en un periodo de tiempo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = NameCaseTest.CINTA_AUDITORA_OK_200),
            @ApiResponse(responseCode = "404", description = NameCaseTest.CINTA_AUDITORA_NOT_FOUND_404),
            @ApiResponse(responseCode = "412", description = NameCaseTest.CINTA_AUDITORAS_OBSOLETA_412),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
            @ApiResponse(responseCode = "503", description = NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503)
    })
    @PostMapping("cintas-auditoras")
    public ResponseEntity<List<CintaAuditoraDto>> getCintasAuditoras(@RequestBody CajaRegistradoraDto cajaRegistradora) throws NotFoundException, FeignClientException, NotFoundCausedException {
        WebResponse response = cintaAuditoraService.getCintaAuditora(cajaRegistradora);
        return handleApiResponseToObjectList(response, MessageText.ENDPOINT_CINTAS_AUDITORAS_BY_CAJA_REGGISTERADORA);
    }
}
