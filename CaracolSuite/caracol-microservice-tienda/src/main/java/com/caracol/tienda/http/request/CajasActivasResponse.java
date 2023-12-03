package com.caracol.tienda.http.request;

import lombok.Data;

import java.util.List;

@Data
public class CajasActivasResponse {
    List<CajaDto> cajas;
}
