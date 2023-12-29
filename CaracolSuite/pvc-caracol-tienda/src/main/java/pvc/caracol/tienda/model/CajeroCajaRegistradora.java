package pvc.caracol.tienda.model;

import lombok.Data;

import java.util.List;

@Data
public class CajeroCajaRegistradora {
    private int idTrabajador;
    private int codigoCajero;
    private String denominacion;
    private boolean activo;
    private CajaRegistradora cajaRegistradora;
    List<LogCajero> logCajeros;
    List<RegistroOperacionesCajeros> registroOperacionesCajeros;
}
