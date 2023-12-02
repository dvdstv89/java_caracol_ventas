package com.caracol.mistral.model;

import com.caracol.mistral.enums.ModeloCaja;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Caja {
    private String id;
    private String centroCosto;
    private String numeroCaja;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private ModeloCaja modelo;
}
