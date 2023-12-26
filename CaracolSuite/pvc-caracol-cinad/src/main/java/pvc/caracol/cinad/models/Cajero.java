package pvc.caracol.cinad.models;

import lombok.Data;

import java.util.List;

@Data
public class Cajero {
    private String codigo;
    private boolean haceFuncionSupervisor;
    private int cantidadCorrecciones;
    private int cantidadTVOID;
    private int cantidadRFUND;
    private int cantidadClientes;
    private int cantidadReportes;
    private double propina;
    private double recaudacion;
    private double cantidadProductos;
    private double cantidadInsumos;
    private List<Pago> formasPago;

    public Cajero(String codigo) {
        this.codigo = codigo;
    }
}
