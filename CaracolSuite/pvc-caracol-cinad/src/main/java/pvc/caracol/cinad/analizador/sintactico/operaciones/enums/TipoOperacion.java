package pvc.caracol.cinad.analizador.sintactico.operaciones.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Getter
public enum TipoOperacion {
    REFUND("Refund",NaturalezaOperacion.Devolucion, true),
    TVOID("T-Void", NaturalezaOperacion.Devolucion, true),
    PROPINA("Propina", NaturalezaOperacion.Venta, false),
    VENTA("Venta", NaturalezaOperacion.Venta, false),
    REPORTE_TERMINAL("R1",NaturalezaOperacion.Reporte, true),
    REPORTE_CAJERO("R2",NaturalezaOperacion.Reporte, true),
    REPORTE_PLU("R3",NaturalezaOperacion.Reporte, true),
    X1_REPORTE_TERMINAL("X1",NaturalezaOperacion.Reporte, true),
    X2_REPORTE_CAJERO("X2",NaturalezaOperacion.Reporte, true),
    X3_REPORTE_PLU("X3",NaturalezaOperacion.Reporte, true),
    Z1_REPORTE_TERMINAL("Z1",NaturalezaOperacion.Reporte, true),
    Z2_REPORTE_CAJERO("Z2",NaturalezaOperacion.Reporte, true),
    Z3_REPORTE_PLU("Z3",NaturalezaOperacion.Reporte, true),
    DECLARACION_EFECTIVO("DECLARACION_EFECTIVO", NaturalezaOperacion.NoDefined, false),
    ENTRADA_FONDO("ENTRADA_FONDO", NaturalezaOperacion.NoDefined, false),
    EXTRACCION_FONDO("EXTRACCION_FONDO", NaturalezaOperacion.NoDefined, false),
    EXTRACCION_CUP("EXTRACCION_CUP", NaturalezaOperacion.NoDefined, false),
    MANTENIMIENTO_PLU_MISTRAL("MANTENIMIENTO_PLU_MISTRAL", NaturalezaOperacion.Mantenimiento, false),
    MANTENIMIENTO("MANTENIMIENTO", NaturalezaOperacion.Mantenimiento, false),
    OPERACION_SKIPED("OPERACION_SKIPED", NaturalezaOperacion.NoDefined, false),
    SALVA_AUTOMATICA("SALVA_AUTOMATICA", NaturalezaOperacion.Mantenimiento, false),
    DESCONOCIDO("DESCONOCIDO", NaturalezaOperacion.NoDefined, false),
    ENCENDER_CAJA("ENCENDER_CAJA", NaturalezaOperacion.Mantenimiento, false),
    INDEFINIDA("INDEFINIDA", NaturalezaOperacion.NoDefined, false),
    CREAR_PRODUCTO_MANUAL("CREAR_PRODUCTO_MANUAL", NaturalezaOperacion.Mantenimiento, true);

    private final NaturalezaOperacion naturalezaOperacion;
    private final String description;
    private final Boolean supervisorRoleNeeded;

    TipoOperacion(String description, NaturalezaOperacion naturalezaOperacion, Boolean supervisorRoleNeeded) {
        this.description = description;
        this.naturalezaOperacion = naturalezaOperacion;
        this.supervisorRoleNeeded = supervisorRoleNeeded;
    }

    private static final Map<String, TipoOperacion> naturalezaOperacionToTipoOperacionMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(TipoOperacion::getDescription, Function.identity()));

    @Override
    public String toString() {
        return description;
    }
}
