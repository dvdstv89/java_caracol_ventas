package com.system.mistral.http.output;

import com.system.mistral.dtos.CajaDto;
import lombok.Data;

import java.util.List;

@Data
public class CajasActivasDto {
    private List<CajaDto> cajas;
}
