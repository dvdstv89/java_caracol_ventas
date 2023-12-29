package pvc.caracol.empresarial.services;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;

public interface IDataBaseMistralService {
    WebResponse findBaseDatosMistralByCodeCentroGestion(String code) throws NotFoundException;
    WebResponse updateDatabaseConnexion(String code, DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException;
}
