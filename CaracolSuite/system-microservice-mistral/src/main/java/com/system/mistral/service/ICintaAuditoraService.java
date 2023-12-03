package com.system.mistral.service;

import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.http.output.CintaAuditoraResponse;

public interface ICintaAuditoraService {
    CintaAuditoraResponse getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest);
}
