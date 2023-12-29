package pvc.caracol.tienda.service;

import pvc.caracol.common.reponse.WebResponse;

public interface ITiendaService {
    WebResponse findCajasActivasByCodeCentroGestion(String code);
}
