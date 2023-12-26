package pvc.caracol.empresarial.controller;

import pvc.caracol.empresarial.http.output.DataBaseMistralDTO;
import pvc.caracol.empresarial.services.ICentroGestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/centro-gestion")
public class CentroGestionController {
    private final ICentroGestionService service;

      @Autowired
      public CentroGestionController(ICentroGestionService service) {
          this.service = service;
      }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo empresaroial");
    }

    @GetMapping("database-mistral/{code}")
    public ResponseEntity<DataBaseMistralDTO> findBaseDatosMistralByCodeCentroGestion(@PathVariable String code) {
        return ResponseEntity.ok(service.findBaseDatosMistralByCodeCentroGestion(code));
    }
}
