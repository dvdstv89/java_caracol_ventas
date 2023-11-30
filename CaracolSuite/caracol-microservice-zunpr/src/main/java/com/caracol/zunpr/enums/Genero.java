package com.caracol.zunpr.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Genero {
    Masculino("M"),
    Femenino("F");

    private final String sexo;
    Genero(String sexo) {
        this.sexo = sexo;
    }
    public String getSexo() {
        return sexo;
    }

    private static final Map<String, Genero> sexoToGeneroMap = Arrays
            .stream(values())
            .collect(
                    Collectors.toMap(Genero::getSexo, Function.identity()))
            ;

    public static Genero fromSexo(String sexo) {
        if (sexo == null) {
            return null;
        }
        return sexoToGeneroMap.get(sexo);
    }
}
