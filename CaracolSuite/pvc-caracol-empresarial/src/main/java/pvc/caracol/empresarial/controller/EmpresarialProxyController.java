package pvc.caracol.empresarial.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pvc.caracol.common.controller.BaseController;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;
import pvc.caracol.empresarial.messages.MessageText;
import pvc.caracol.empresarial.services.IDataBaseMistralService;

@RestController
@RequestMapping("/api/v1/empresarial")
public class EmpresarialProxyController extends BaseController {
    private final IDataBaseMistralService service;

    @Autowired
    public EmpresarialProxyController(IDataBaseMistralService service, Logger logger) {
        super(logger);
        this.service = service;
    }

    @GetMapping("database-mistral/{code}")
    public ResponseEntity<DataBaseMistralDTO> findBaseDatosMistralByCodeCentroGestion(@PathVariable String code) throws NotFoundException {
        WebResponse response = service.findBaseDatosMistralByCodeCentroGestion(code);
        return handleApiResponseToObject(response, MessageText.ENDPOINT_FIND_DATE_DATABASE_MISTRAL);
    }
}
