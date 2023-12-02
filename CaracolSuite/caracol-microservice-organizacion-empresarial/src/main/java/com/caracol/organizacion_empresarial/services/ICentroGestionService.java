package com.caracol.organizacion_empresarial.services;

import com.caracol.organizacion_empresarial.http.output.DataBaseMistralDTO;

public interface ICentroGestionService {
    DataBaseMistralDTO findBaseDatosMistralByCodeCentroGestion(String code);
}
