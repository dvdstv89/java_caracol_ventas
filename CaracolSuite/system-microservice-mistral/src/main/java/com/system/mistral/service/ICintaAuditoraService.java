package com.system.mistral.service;

import com.system.mistral.dtos.CintaAuditoraDto;
import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.http.output.CintaAuditoraResponse;
import com.system.mistral.http.output.FicheroCintaAuditoraResponse;

import java.io.IOException;
import java.util.List;

public interface ICintaAuditoraService {
    CintaAuditoraResponse getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest);
    FicheroCintaAuditoraResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException;
}
