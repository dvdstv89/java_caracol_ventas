package com.caracol.mistral.http.output;

import com.caracol.mistral.dtos.CajaDto;
import lombok.Data;

import java.util.List;

@Data
public class CajasActivasDto {
    private List<CajaDto> cajas;
}
