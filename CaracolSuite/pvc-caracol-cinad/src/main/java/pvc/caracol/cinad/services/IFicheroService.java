package pvc.caracol.cinad.services;

import pvc.caracol.cinad.models.FicheroCintaAuditora;

import java.io.IOException;

public interface IFicheroService {
    FicheroCintaAuditora decodificarFichero(byte[] fichero) throws IOException;
}
