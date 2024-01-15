package pvc.caracol.empresarial.services;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;

public interface ITiendaService {
    ApiWebResponse findTiendaById(String codeTienda) throws NotFoundException;
}
