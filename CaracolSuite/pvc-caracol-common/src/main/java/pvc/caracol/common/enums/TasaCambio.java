package pvc.caracol.common.enums;

import pvc.caracol.common.models.RangoTasaCambio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
public class TasaCambio {
    public static final TipoMoneda monedaBase = TipoMoneda.CUP;
    public static final Double tazaCambioPorDefault = 120.0;
    private static final List<RangoTasaCambio> tasasCambio = new ArrayList<>();

    static {
        tasasCambio.add(new RangoTasaCambio(LocalDate.of(2000, 1, 1),
                LocalDate.of(2021, 12, 31), 24.0));

        tasasCambio.add(new RangoTasaCambio(LocalDate.of(2022, 1, 1),
                LocalDate.of(2030, 12, 31), 120.0));
    }

    public static Double obtenerTasaCambio(LocalDate fecha) {
        for (RangoTasaCambio rango : tasasCambio) {
            if (fecha.isEqual(rango.getFechaInicio()) || (fecha.isAfter(rango.getFechaInicio()) && fecha.isBefore(rango.getFechaFin()))) {
                return rango.getValorTasaCambio();
            }
        }
        return tazaCambioPorDefault;
    }
}
