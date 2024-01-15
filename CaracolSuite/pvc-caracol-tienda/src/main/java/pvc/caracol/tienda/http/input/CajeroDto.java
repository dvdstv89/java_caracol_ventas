package pvc.caracol.tienda.http.input;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CajeroDto {
    private String codigo;
    private boolean haceFuncionSupervisor;
    private int cantidadCorrecciones;
    private int cantidadTVOID;
    private int cantidadRFUND;
    private int cantidadClientes;
    private int cantidadReportes;
    private double cantidadProductos;
    private double cantidadInsumos;
    private List<PagoDto> ventas;
    private List<PagoDto> propinas;
}

