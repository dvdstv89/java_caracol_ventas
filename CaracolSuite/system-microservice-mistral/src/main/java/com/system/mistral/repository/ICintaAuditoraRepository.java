package com.system.mistral.repository;

import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.model.Caja;
import com.system.mistral.model.CintaAuditora;

import java.util.List;

public interface ICintaAuditoraRepository {
    List<CintaAuditora> getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest);
}
