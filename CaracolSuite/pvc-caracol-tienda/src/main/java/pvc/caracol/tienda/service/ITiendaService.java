package pvc.caracol.tienda.service;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;

public interface ITiendaService {
    WebResponse findCajasActivasByCodeCentroGestion(String code) throws NotFoundException, FeignClientException;
}
