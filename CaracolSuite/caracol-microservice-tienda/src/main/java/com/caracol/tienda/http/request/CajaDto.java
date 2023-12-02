package com.caracol.tienda.http.request;

import lombok.Data;

@Data
public class CajaDto {
    private String id;
    private String centroCosto;
    private String numeroCaja;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private String modelo;
}
