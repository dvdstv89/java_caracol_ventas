package com.caracol.mistral.service;

import com.caracol.mistral.dtos.CajaDto;
import com.caracol.mistral.http.output.CajasActivasDto;
import com.caracol.mistral.model.Caja;
import com.caracol.mistral.repository.ICajaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajaService implements ICajaService {

    private final ICajaRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public CajaService(ICajaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Caja> getCajasActivas() {
        return repository.getCajasActivas("23205");
    }

    @Override
    public CajasActivasDto getCajasActivasCentroGestion(String centroGestion) {
        List<Caja> cajas = repository.getCajasActivas(centroGestion);
        CajasActivasDto cajasActivasDto = new CajasActivasDto();
        cajasActivasDto.setCajas(
                cajas.stream()
                        .map(c -> modelMapper.map(c, CajaDto.class))
                        .collect(Collectors.toList())
        );
        return cajasActivasDto;
    }
}
