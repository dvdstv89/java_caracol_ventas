package com.caracol.tienda.http.response;

import lombok.Data;

import java.util.List;

@Data
public class CajasActivasByCodeCentroGestionResponse {
    private String complejo;
    private String centroGestion;
    private List<?> cajasRegistradorasDto;
}
