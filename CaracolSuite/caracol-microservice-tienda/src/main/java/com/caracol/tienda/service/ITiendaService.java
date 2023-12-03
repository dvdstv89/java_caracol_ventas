package com.caracol.tienda.service;

import com.caracol.tienda.http.request.CajasActivasResponse;

import java.util.List;

public interface ITiendaService {
     CajasActivasResponse findCajasActivasByCodeCentroGestion(String code);
}
