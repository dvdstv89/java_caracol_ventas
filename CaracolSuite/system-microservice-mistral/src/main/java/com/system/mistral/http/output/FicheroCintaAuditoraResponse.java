package com.system.mistral.http.output;

import com.system.mistral.dtos.FicheroCintaAuditoraDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FicheroCintaAuditoraResponse {
    private List<FicheroCintaAuditoraDto> ficheroCintaAuditora;
}
