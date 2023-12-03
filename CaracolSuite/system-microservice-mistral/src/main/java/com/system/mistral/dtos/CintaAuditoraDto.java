package com.system.mistral.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class CintaAuditoraDto {
    private String codigoAlmacen;
    private String codigoRed;
    private String idCaja;
    private Date fechaCreacion;
    private Date fechaProcesado;
    private byte[] fichero;
}
