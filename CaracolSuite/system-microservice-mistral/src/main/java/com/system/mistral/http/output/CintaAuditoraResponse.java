package com.system.mistral.http.output;

import com.system.mistral.dtos.CintaAuditoraDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CintaAuditoraResponse {
    private List<CintaAuditoraDto> cintaAuditora;
}
