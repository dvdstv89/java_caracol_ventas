package pvc.caracol.empresarial.services;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;

public interface IDataBaseMistralService {
    ApiWebResponse findBaseDatosMistralByCodeCentroGestion(Integer idCentroGestion) throws NotFoundException;

    ApiWebResponse updateDatabaseConnexion(Integer idCentroGestion, DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException;
}
