package com.caracol.tienda.service;

import com.caracol.tienda.http.request.CajasActivasDto;

public interface ITiendaService {
    public CajasActivasDto findCajasActivasByCodeCentroGestion(String code);
}
