package com.caracol.mistral.repository;

import com.caracol.mistral.model.Caja;
import java.util.List;

public interface ICajaRepository {
    List<Caja> getCajasActivas(String centroGestion);
}
