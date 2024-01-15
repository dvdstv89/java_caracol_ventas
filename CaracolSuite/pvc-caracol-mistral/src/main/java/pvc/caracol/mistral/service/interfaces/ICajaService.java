package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;

public interface ICajaService {

    ApiWebResponse getCajasActivasByCentroGestion(Integer idCentroGestion) throws NotFoundException, FeignClientException;

    ApiWebResponse getCajasActivasByTienda(String codigoTienda) throws NotFoundException, FeignClientException;
}
