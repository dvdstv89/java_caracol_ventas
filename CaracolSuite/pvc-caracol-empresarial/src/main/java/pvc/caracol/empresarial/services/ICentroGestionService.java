package pvc.caracol.empresarial.services;

import pvc.caracol.empresarial.http.output.DataBaseMistralDTO;

public interface ICentroGestionService {
    DataBaseMistralDTO findBaseDatosMistralByCodeCentroGestion(String code);
}
