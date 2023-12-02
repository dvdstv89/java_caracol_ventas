package com.caracol.tienda.service;

import com.caracol.tienda.client.IMistralClient;
import com.caracol.tienda.http.request.CajasActivasDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaService implements ITiendaService {
    private final ModelMapper modelMapper;
    private final IMistralClient mistralClient;

    @Autowired
    public TiendaService(ModelMapper modelMapper, IMistralClient mistralClient) {
        this.modelMapper = modelMapper;
        this.mistralClient = mistralClient;
    }

    @Override
    public CajasActivasDto findCajasActivasByCodeCentroGestion(String code) {
        return mistralClient.getCajasActivasCentroGestion(code);
    }
}