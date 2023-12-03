package com.system.mistral.http.input;

import lombok.Data;

import java.util.Date;

@Data
public class CintaAuditoraRequest {
    private Date fechaInicio;
    private Date fechaFin;
    private String codigoCentroGestion;
    private String codigoRed;
    private Integer idCajaRegistradora;
}
