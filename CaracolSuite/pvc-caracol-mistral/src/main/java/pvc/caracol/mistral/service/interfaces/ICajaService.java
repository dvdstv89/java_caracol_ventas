package pvc.caracol.mistral.service.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;

public interface ICajaService {

    WebResponse getCajasActivasByCentroGestion(String centroGestion) throws NotFoundException;
}
