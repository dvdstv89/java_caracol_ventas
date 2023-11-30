package com.caracol.zunpr.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Raza {
    Blanca("B"),
    Mestiza("M"),
    Negra("N");

    private final String color;
    Raza(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    private static final Map<String, Raza> colorToRazaMap = Arrays
            .stream(values())
            .collect(
                    Collectors.toMap(Raza::getColor, Function.identity()))
            ;

    public static Raza fromColorPiel(String color) {
        if (color == null) {
            return null;
        }
        return colorToRazaMap.get(color);
    }
}
