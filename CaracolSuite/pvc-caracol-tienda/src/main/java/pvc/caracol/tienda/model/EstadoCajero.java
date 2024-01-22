package pvc.caracol.tienda.model;

public enum EstadoCajero {
    Activo, //alta del Trabajador como cajero a la tienda
    Desactivo, //baja del trabajador como cajero de la tienda
    Vacaciones, //vacaciones planificadas
    Franco, //franco
    AusenciaInjustificada, //ausencia injustificada
    AusenciaJustificada
}
