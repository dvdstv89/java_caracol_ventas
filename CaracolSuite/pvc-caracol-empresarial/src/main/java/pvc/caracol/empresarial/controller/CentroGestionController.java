package pvc.caracol.empresarial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;
import pvc.caracol.empresarial.http.output.TiendaDto;
import pvc.caracol.empresarial.messages.MessageText;
import pvc.caracol.empresarial.messages.NameCaseTest;
import pvc.caracol.empresarial.services.IDataBaseMistralService;
import pvc.caracol.empresarial.services.ITiendaService;

@RestController
@Tag(name = "Empresarial", description = "Obtener informacion sobre la estructura de la empresa")
@RequestMapping("/api/v1/centro-gestion")
public class CentroGestionController extends BaseController {
    private final IDataBaseMistralService dataBaseMistralService;
    private final ITiendaService tiendaService;

    @Autowired
    public CentroGestionController(IDataBaseMistralService dataBaseMistralService, ITiendaService tiendaService, Logger logger) {
        super(logger);
        this.dataBaseMistralService = dataBaseMistralService;
        this.tiendaService = tiendaService;
    }

    @Operation(
            summary = "Obtener base de datos de mistral",
            description = "Obtener los metadatos de la base de datos de Mistral de un centro de gestión específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NameCaseTest.DATABASE_MISTRAL_OK_200),
            @ApiResponse(responseCode = "400", description = NameCaseTest.DATABASE_MISTRAL_NOT_FOUND_404),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
    })
    @PostMapping("database-mistral/{idCentroGestion}")
    public ResponseEntity<ApiWebResponse> findBaseDatosMistralByCodeCentroGestion(@PathVariable Integer idCentroGestion) throws NotFoundException {
        ApiWebResponse response = dataBaseMistralService.findBaseDatosMistralByCodeCentroGestion(idCentroGestion);
        return handleApiResponse(response, MessageText.ENDPOINT_FIND_DATE_DATABASE_MISTRAL);
    }

    @Operation(
            summary = "Actualizar base de datos de mistral",
            description = "Actualizar los metadatos de la base de datos de Mistral de un centro de gestión específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NameCaseTest.DATABASE_MISTRAL_MODIFICADO_OK_200),
            @ApiResponse(responseCode = "400", description = NameCaseTest.DATABASE_MISTRAL_NOT_FOUND_404),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
    })
    @PutMapping("update-database-mistral/{idCentroGestion}")
    public ResponseEntity<ApiWebResponse> updateBaseDatosMistral(@PathVariable Integer idCentroGestion, @RequestBody DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException {
        ApiWebResponse apiWebResponse = dataBaseMistralService.updateDatabaseConnexion(idCentroGestion, dataBaseMistralDTO);
        return handleApiResponse(apiWebResponse, MessageText.ENDPOINT_UPDATE_DATABASE_MISTRAL);
    }

    @Operation(
            summary = "Obtener tienda",
            description = "Obtener la tienda por su id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = NameCaseTest.TIENDA_OK_200),
            @ApiResponse(responseCode = "400", description = NameCaseTest.TIENDA_NOT_FOUND_404),
            @ApiResponse(responseCode = "500", description = NameCaseTest.GENERIC_ERROR_500),
    })
    @PostMapping ("tienda/{idTienda}")
    public ResponseEntity<ApiWebResponse> findTiendaByIdTienda(@PathVariable String idTienda) throws NotFoundException {
        ApiWebResponse response = tiendaService.findTiendaById(idTienda);
        return handleApiResponse(response, MessageText.ENDPOINT_FIND_TIENDA);
    }
}
