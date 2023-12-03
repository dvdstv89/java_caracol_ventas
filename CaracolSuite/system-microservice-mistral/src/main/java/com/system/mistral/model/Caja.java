package com.system.mistral.model;

import com.system.mistral.enums.ModeloCaja;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Caja {
    private String id;
    private String codigoRed;
    private String centroCosto;
    private String numeroCaja;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private ModeloCaja modelo;
}
