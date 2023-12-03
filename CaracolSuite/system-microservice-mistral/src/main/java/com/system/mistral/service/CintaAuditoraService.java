package com.system.mistral.service;

import com.system.mistral.dtos.CajaDto;
import com.system.mistral.dtos.CintaAuditoraDto;
import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.http.output.CajasActivasResponse;
import com.system.mistral.http.output.CintaAuditoraResponse;
import com.system.mistral.model.Caja;
import com.system.mistral.model.CintaAuditora;
import com.system.mistral.repository.ICintaAuditoraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CintaAuditoraService implements ICintaAuditoraService {

    private final ICintaAuditoraRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ICintaAuditoraRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public CintaAuditoraResponse getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) {
        List<CintaAuditora> cintaAuditoras = repository.getCintaAuditora(cintaAuditoraRequest);
        CintaAuditoraResponse response = CintaAuditoraResponse.builder()
                .cintaAuditora( cintaAuditoras.stream().map(c -> modelMapper.map(c, CintaAuditoraDto.class)).toList())
                .build();
        return response;
        //todo microservicio que reciba una cinta auditora comprimida y de toda la informacion que se pueda. 1 sola cinta
    }
}
