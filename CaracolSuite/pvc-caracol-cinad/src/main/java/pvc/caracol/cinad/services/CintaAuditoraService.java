package pvc.caracol.cinad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.cinad.analizador.lexico.ILexer;
import pvc.caracol.cinad.analizador.lexico.Lexer;
import pvc.caracol.cinad.analizador.sintactico.IParser;
import pvc.caracol.cinad.analizador.sintactico.Parser;
import pvc.caracol.cinad.models.CintaAuditoraElectronica;
import pvc.caracol.cinad.models.FicheroCintaAuditora;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.common.service.BaseService;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    private final IFicheroService ficheroService;

    @Autowired
    public CintaAuditoraService() {
        this.ficheroService = new FicheroService();
    }

    public ApiResponse analizarCintaAuditora(byte[] fichero) throws Exception {
        FicheroCintaAuditora ficheroCintaAuditora = ficheroService.decodificarFichero(fichero);
        ILexer lexer = new Lexer(ficheroCintaAuditora.getSourceStream());
        IParser parser = new Parser(lexer);
        CintaAuditoraElectronica cintaAuditoraElectronica = parser.parse();
        response.addOkResponse200(cintaAuditoraElectronica);
        return response;
    }
}
