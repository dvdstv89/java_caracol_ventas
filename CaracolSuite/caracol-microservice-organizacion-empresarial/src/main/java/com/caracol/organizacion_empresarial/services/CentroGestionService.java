package com.caracol.organizacion_empresarial.services;

import com.caracol.organizacion_empresarial.http.output.DataBaseMistralDTO;
import com.caracol.organizacion_empresarial.models.CentroGestion;
import com.caracol.organizacion_empresarial.models.DataBaseMistral;
import com.caracol.organizacion_empresarial.repositories.ICentroGestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroGestionService implements ICentroGestionService{
    private final ICentroGestionRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public CentroGestionService(ICentroGestionRepository repository, ModelMapper modelMapper){
        this.repository=repository;
        this.modelMapper = modelMapper;
    }
    @Override
    public DataBaseMistralDTO findBaseDatosMistralByCodeCentroGestion(String code) {
        CentroGestion centroGestion = repository.findByCode(code);
        if (centroGestion != null){
            DataBaseMistral dataBaseMistral = centroGestion.getDatabase();
            DataBaseMistralDTO dataBaseMistralDTO = modelMapper.map(dataBaseMistral, DataBaseMistralDTO.class);
            return  dataBaseMistralDTO;
        }
        return null; // todo control de excepciones
    }
}
