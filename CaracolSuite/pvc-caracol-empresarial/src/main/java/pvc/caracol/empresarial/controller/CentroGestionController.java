package pvc.caracol.empresarial.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;
import pvc.caracol.empresarial.services.IDataBaseMistralService;

@RestController
@RequestMapping("/api/v1/centro-gestion")
public class CentroGestionController extends BaseController {
    private final IDataBaseMistralService service;

    @Autowired
    public CentroGestionController(IDataBaseMistralService service, Logger logger) {
        super(logger);
        this.service = service;
    }

    @GetMapping("database-mistral/{code}")
    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error interno del servidor")
    })
    public ResponseEntity<WebResponse> findBaseDatosMistralByCodeCentroGestion(@PathVariable String code) throws NotFoundException {
        return ResponseEntity.ok(service.findBaseDatosMistralByCodeCentroGestion(code));
    }

    @PutMapping("update-database-mistral/{code}")
    public ResponseEntity<WebResponse> updateBaseDatosMistral(@PathVariable String code, @RequestBody DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException {
        return ResponseEntity.ok(service.updateDatabaseConnexion(code, dataBaseMistralDTO));
    }
}
