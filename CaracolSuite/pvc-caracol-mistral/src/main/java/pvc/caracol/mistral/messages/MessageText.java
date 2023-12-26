package pvc.caracol.mistral.messages;

import pvc.caracol.mistral.SystemMicroserviceMistralApplication;

public class MessageText {
    public static final String CINTA_AUDITORA_OBSOLETA = "La fecha minima para comenzar a anilzar cintas auditoras es "+ SystemMicroserviceMistralApplication.fechaMinimaBuscarCintas.toString();
    public static final String CINTA_AUDITORA_NOT_FOUND = "No se encontraron cintas auditoras";

}
