package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiResponse;

public interface ICajaService {

    ApiResponse getCajasActivasByCentroGestion(String centroGestion) throws NotFoundException;
}
