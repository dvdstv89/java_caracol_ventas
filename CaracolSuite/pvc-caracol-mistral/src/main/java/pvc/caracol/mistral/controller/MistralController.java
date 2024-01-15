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
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.messages.MessageText;
import pvc.caracol.mistral.messages.NameCaseTest;
import pvc.caracol.mistral.service.interfaces.ICajaService;
import pvc.caracol.mistral.service.interfaces.ICintaAuditoraService;

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
    @PostMapping("cajas-activas-centro-gestion/{idCentroGestion}")
    public ResponseEntity<ApiWebResponse> getCajasActivasByIdCentroGestion(@PathVariable Integer idCentroGestion) throws NotFoundException, FeignClientException {
        ApiWebResponse response = cajaService.getCajasActivasByCentroGestion(idCentroGestion);
        return handleApiResponse(response, MessageText.ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_CENTRO_GESTION);
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
    @PostMapping("cajas-activas-tienda/{codigoTienda}")
    public ResponseEntity<ApiWebResponse> getCajasActivasByTienda(@PathVariable String codigoTienda) throws NotFoundException, FeignClientException {
        ApiWebResponse response = cajaService.getCajasActivasByTienda(codigoTienda);
        return handleApiResponse(response, MessageText.ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_TIENDA);
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
    public ResponseEntity<ApiWebResponse> getCintasAuditoras(@RequestBody CajaRegistradoraDto cajaRegistradoraDto) throws NotFoundException, FeignClientException, NotFoundCausedException {
        ApiWebResponse response = cintaAuditoraService.getCintaAuditora(cajaRegistradoraDto);
        return handleApiResponse(response, MessageText.ENDPOINT_CINTAS_AUDITORAS_BY_CAJA_REGGISTERADORA);
    }
}
