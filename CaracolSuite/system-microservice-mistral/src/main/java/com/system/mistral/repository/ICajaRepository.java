package com.system.mistral.repository;

import com.system.mistral.model.Caja;

import java.util.List;

public interface ICajaRepository {
    List<Caja> getCajasActivas(String centroGestion);
}
