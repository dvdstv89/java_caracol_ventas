package pvc.caracol.tienda.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.tienda.http.output.TiendaDto;
import pvc.caracol.tienda.messages.NameCaseTest;
import pvc.caracol.tienda.service.interfaces.ICintaAuditoraService;

@RestController
@Tag(name = "Tiendas", description = "Operaciones de las tiendas")
@RequestMapping("/api/v1/tienda")
public class TiendaController {

    private final ICintaAuditoraService cintaAuditoraService;

    @Autowired
    public TiendaController(ICintaAuditoraService cintaAuditoraService) {
        this.cintaAuditoraService = cintaAuditoraService;
    }

    @Operation(
            summary = "Obtener las cintas auditoras, analizarlas y guardar los metadatos",
            description = "Busca las cintas auditoras de las caja registradoras  de una tienda especifica en un periodo de tiempo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = NameCaseTest.CINTA_AUDITORA_OK_200),
            @ApiResponse(responseCode = "404", description = NameCaseTest.CINTA_AUDITORA_NOT_FOUND_404),
            @ApiResponse(responseCode = "412", description = NameCaseTest.CINTA_AUDITORAS_OBSOLETA_412),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
            @ApiResponse(responseCode = "503", description = NameCaseTest.GENERIC_INSTANCIA_NOT_FOUND_503)
    })
    @PostMapping("analizar-cintas-auditoras-by-tienda")
    public ResponseEntity<ApiWebResponse> analizarCintasAuditorasByTienda(@RequestBody TiendaDto tiendaDto) throws FeignClientException {
        return ResponseEntity.ok(cintaAuditoraService.getCintasAuditorasByTiendaProcesadas(tiendaDto));
    }
}
