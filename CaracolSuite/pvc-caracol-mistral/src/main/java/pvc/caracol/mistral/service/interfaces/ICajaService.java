package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.mistral.model.Caja;

import java.util.List;

public interface ICajaService {
    List<Caja> getCajasActivas() throws NotFoundException;

    ApiResponse getCajasActivasByCentroGestion(String centroGestion) throws NotFoundException;
}
