package pvc.caracol.common.models;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RangoTasaCambio {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double valorTasaCambio;

    public RangoTasaCambio(LocalDate fechaInicio, LocalDate fechaFin, Double valorTasaCambio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.valorTasaCambio = valorTasaCambio;
    }
}
