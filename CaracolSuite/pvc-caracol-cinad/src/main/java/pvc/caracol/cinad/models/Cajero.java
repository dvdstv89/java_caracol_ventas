package pvc.caracol.cinad.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.sintactico.models.CorreccionVenta;
import pvc.caracol.cinad.analizador.sintactico.models.ProductoOperacion;
import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.utils.ProcesarOperacionUtil;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Cajero {
    private String codigo;
    private boolean haceFuncionSupervisor;
    private int cantidadCorrecciones;
    private int cantidadTVOID;
    private int cantidadRFUND;
    private int cantidadClientes;
    private int cantidadReportes;
    private double cantidadProductos;
    private double cantidadInsumos;
    private List<Pago> ventas;
    private List<Pago> propinas;

    public void increseCantidadClientes() {
        cantidadClientes++;
    }

    public void increseReportes() {
        cantidadReportes++;
        haceFuncionSupervisor = true;
    }

    public void increseProductos(List<VentaProducto> productos) {
        cantidadProductos += productos.stream()
                .filter(producto -> !producto.getProducto().getIsInsumo())
                .mapToDouble(ProductoOperacion::getCantidad)
                .sum();
        cantidadInsumos += productos.stream()
                .filter(producto -> producto.getProducto().getIsInsumo())
                .mapToDouble(ProductoOperacion::getCantidad)
                .sum();
    }

    public void increseRefund() {
        cantidadRFUND++;
        haceFuncionSupervisor = true;
    }

    public void increseTVoid() {
        cantidadTVOID++;
        haceFuncionSupervisor = true;
    }

    public void increseCorrecciones(List<CorreccionVenta> correccionVentas) {
        if (!correccionVentas.isEmpty()) {
            cantidadCorrecciones += correccionVentas.size();
        }
    }

    public void addPropina(List<Pago> pagos) {
        pagos.forEach(pago -> ProcesarOperacionUtil.addPago(propinas, pago));
    }

    public void addVentas(List<Pago> pagos) {
        pagos.forEach(pago -> ProcesarOperacionUtil.addPago(ventas, pago));
    }
}
