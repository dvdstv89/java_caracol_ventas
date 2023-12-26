package pvc.caracol.cinad.analizador.sintactico;

import pvc.caracol.cinad.models.CintaAuditoraElectronica;

public interface IParser {
    CintaAuditoraElectronica parse() throws Exception;
}
