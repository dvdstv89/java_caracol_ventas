package com.system.zunpr.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Provincia {
    PINAR_DEL_RIO(1, "PINAR DEL RIO"),
    ARTEMISA(2, "ARTEMISA"),
    HABANA(3, "LA HABANA"),
    MAYABEQUE(4, "MAYABEQUE");

    private final Integer codigo;
    private final String nombre;

    private static final Map<Integer, Provincia> codigoToProvinciaMap =  Arrays
            .stream(values())
            .collect(
                    Collectors.toMap(Provincia::getCodigo, Function.identity()))
            ;

    public Integer getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }

    Provincia(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public static Provincia fromCodigo(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        return codigoToProvinciaMap.get(codigo);
    }
}
