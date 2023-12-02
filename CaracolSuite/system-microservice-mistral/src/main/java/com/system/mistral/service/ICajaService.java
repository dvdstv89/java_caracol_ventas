package com.system.mistral.service;

import com.system.mistral.http.output.CajasActivasDto;
import com.system.mistral.model.Caja;

import java.util.List;

public interface ICajaService {
    List<Caja> getCajasActivas();

    CajasActivasDto getCajasActivasCentroGestion(String centroGestion);
}
