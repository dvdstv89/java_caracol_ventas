package com.system.mistral.service;

import com.system.mistral.http.output.CajasActivasResponse;
import com.system.mistral.model.Caja;

import java.util.List;

public interface ICajaService {
    List<Caja> getCajasActivas();

    CajasActivasResponse getCajasActivasCentroGestion(String centroGestion);
}
