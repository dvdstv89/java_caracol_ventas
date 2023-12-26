package pvc.caracol.mistral.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum ModeloCaja {
    Serie2000("CO_2044"),
    Serie3000("CO_3020");

    private final String tipo;
    ModeloCaja(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }

    private static final Map<String, ModeloCaja> serieToModelMap = Arrays
            .stream(values())
            .collect(
                    Collectors.toMap(ModeloCaja::getTipo, Function.identity()))
            ;

    public static ModeloCaja fromSerie(String serie) {
        if (serie == null) {
            return null;
        }
        return serieToModelMap.get(serie);
    }
}