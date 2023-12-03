package com.system.mistral.service;

import com.system.mistral.dtos.CintaAuditoraDto;
import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.http.output.CintaAuditoraResponse;
import com.system.mistral.model.CintaAuditora;
import com.system.mistral.repository.ICintaAuditoraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import steved.cinad.client.CintaAuditoraClient;
import steved.cinad.client.ICintaAuditoraClient;

import java.util.List;

@Service
public class CintaAuditoraService implements ICintaAuditoraService {

    private final ICintaAuditoraRepository repository;
    private final ICintaAuditoraClient procesadorCintasAuditoras;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ICintaAuditoraRepository repository, ModelMapper modelMapper, ICintaAuditoraClient procesadorCintasAuditoras) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.procesadorCintasAuditoras = procesadorCintasAuditoras;
    }

    public CintaAuditoraResponse getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) {
        List<CintaAuditora> cintaAuditoras = repository.getCintaAuditora(cintaAuditoraRequest);
        return CintaAuditoraResponse.builder()
                .cintaAuditora(cintaAuditoras.stream().map(c -> modelMapper.map(c, CintaAuditoraDto.class)).toList())
                .build();
    }

    public void procesarCintasAuditoras(List<CintaAuditora> cintaAuditoras){
        cintaAuditoras.forEach(cintaAuditora -> {
            procesadorCintasAuditoras.procesarCintaAuditora(cintaAuditora.getFichero());
        });
    }
}
