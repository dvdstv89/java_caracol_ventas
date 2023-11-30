package com.caracol.zunpr.model;

import com.caracol.zunpr.enums.Provincia;
import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Direccion {
    private String direccion;
    private String municipio;
    private String provincia;
}