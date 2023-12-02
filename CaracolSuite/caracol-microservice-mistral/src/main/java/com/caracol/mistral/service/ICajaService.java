package com.caracol.mistral.service;

import com.caracol.mistral.model.DataBaseInfo;
import com.caracol.mistral.http.output.CajasActivasDto;
import com.caracol.mistral.model.Caja;

import java.util.List;

public interface ICajaService {
    List<Caja> getCajasActivas();

    CajasActivasDto getCajasActivasCentroGestion(String centroGestion);
}
