package pvc.caracol.tienda.service;

import pvc.caracol.common.reponse.ApiResponse;

public interface ITiendaService {
    ApiResponse findCajasActivasByCodeCentroGestion(String code);
}
