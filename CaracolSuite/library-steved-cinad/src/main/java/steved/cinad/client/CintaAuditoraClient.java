package steved.cinad.client;

import steved.cinad.models.FicheroCintaAuditora;
import steved.cinad.services.FicheroService;

import java.io.IOException;

public class CintaAuditoraClient implements ICintaAuditoraClient{
    private FicheroService fileDecoder;

    public CintaAuditoraClient(){
        fileDecoder = new FicheroService();
    }

    public FicheroCintaAuditora procesarCintaAuditora( byte[] fichero) throws IOException {
        FicheroCintaAuditora ficheroCintaAuditora = fileDecoder.decodificarFichero(fichero);
        return ficheroCintaAuditora;
    }
}
