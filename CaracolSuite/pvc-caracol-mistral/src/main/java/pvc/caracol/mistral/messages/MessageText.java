package pvc.caracol.mistral.messages;

import pvc.caracol.mistral.SystemMicroserviceMistralApplication;

public class MessageText {
    public static final String CINTA_AUDITORA_OBSOLETA = "La fecha minima para comenzar a anilzar cintas auditoras es "+ SystemMicroserviceMistralApplication.fechaMinimaBuscarCintas;
    public static final String CINTA_AUDITORA_NOT_FOUND = "No se encontraron cintas auditoras";
    public static final String CAJA_REGISTRADORAS_NOT_FOUND = "No se encontraron cajas registradoras";
    public static final String DATABASE_MISTRAL_NOT_FOUND = "No se encontro configuracion de base de datos de Mistral para el centro de gestion %s";
    public static final String ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_CENTRO_GESTION = "Obtener las cajas registradoras activas de un centro de gestion especifico";
    public static final String ENDPOINT_CINTAS_AUDITORAS_BY_CAJA_REGGISTERADORA = "Obtener las cintas auditoras de una caja registradora especifica";
    public static final String ENDPOINT_CAJAS_REGISTRADORAS_ACTIVAS_BY_TIENDA = "Obtener las cajas registradoras activas de una tienda especifica";
}
