package com.caracol.tienda.dtos;

import lombok.Data;

@Data
public class DataBaseMistralDto {
    private String host;
    private String name;
    private Integer port;
    private String username;
    private String password;
}
