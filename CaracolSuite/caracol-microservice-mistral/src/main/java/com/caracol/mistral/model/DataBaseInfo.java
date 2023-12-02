package com.caracol.mistral.model;

import lombok.Data;

@Data
public class DataBaseInfo {
    private String host;
    private String name;
    private Integer port;
    private String username;
    private String password;
}
