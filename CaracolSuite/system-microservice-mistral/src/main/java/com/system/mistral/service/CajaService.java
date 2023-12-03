package com.system.mistral.service;

import com.system.mistral.dtos.CajaDto;
import com.system.mistral.http.output.CajasActivasResponse;
import com.system.mistral.model.Caja;
import com.system.mistral.repository.ICajaRepository;
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
    public  CajasActivasResponse getCajasActivasCentroGestion(String centroGestion) {
        List<Caja> cajas = repository.getCajasActivas(centroGestion);
        CajasActivasResponse response = CajasActivasResponse.builder()
                .cajas(
                        cajas.stream()
                        .map(c -> modelMapper.map(c, CajaDto.class))
                        .collect(Collectors.toList())
                )
                .build();
        return response;
    }
}
