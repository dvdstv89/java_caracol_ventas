package steved.cinad.client;

import steved.cinad.models.FicheroCintaAuditora;

import java.io.IOException;

public interface ICintaAuditoraClient {
    FicheroCintaAuditora procesarCintaAuditora(byte[] fichero) throws IOException;
}
