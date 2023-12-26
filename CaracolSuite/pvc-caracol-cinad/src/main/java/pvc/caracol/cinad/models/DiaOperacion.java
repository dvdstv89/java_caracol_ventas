package pvc.caracol.cinad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pvc.caracol.cinad.analizador.sintactico.models.ProductoOperacion;
import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.analizador.sintactico.operaciones.*;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.utils.ProcesarOperacionUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DiaOperacion {
    private String codigoDiaOperacion;
    private LocalDate fecha;
    private List<Pago> formasPago;
    private List<Pago> propinas;
    private List<VentaProducto> productos;
    private List<Cajero> cajeros;
    private List<VentaProducto> devoluciones;
    @JsonIgnore
    private List<IOperacion> operacionesDescuadradas;
    @JsonIgnore
    private int cantidadOperaciones;
    @JsonIgnore
    private List<IOperacion> operaciones;

    @JsonIgnore
    public double getVentasPorProductos() {
        return productos.stream().mapToDouble(ProductoOperacion::getSaldo).sum();
    }

    public DiaOperacion() {
        operaciones = new ArrayList<>();
        formasPago = new ArrayList<>();
        productos = new ArrayList<>();
        cajeros = new ArrayList<>();
        propinas = new ArrayList<>();
        devoluciones = new ArrayList<>();
        operacionesDescuadradas = new ArrayList<>();
        cantidadOperaciones = 0;
    }

    public void addOperation(IOperacion operacion) {
        cantidadOperaciones++;
        operaciones.add(operacion);
        switch (operacion.getTipoOperacion()) {
            case TipoOperacion.TVOID -> procesarTVOID((OperacionTVOID) operacion);
            case TipoOperacion.REFUND -> procesarREFUND((OperacionREFUND) operacion);
            case TipoOperacion.VENTA -> procesarVenta((OperacionVenta) operacion);
            case TipoOperacion.PROPINA -> procesarPropina((OperacionPropina) operacion);
            case TipoOperacion.X1_REPORTE_TERMINAL,
                    TipoOperacion.X2_REPORTE_CAJERO,
                    TipoOperacion.X3_REPORTE_PLU,
                    TipoOperacion.Z1_REPORTE_TERMINAL,
                    TipoOperacion.Z2_REPORTE_CAJERO,
                    TipoOperacion.Z3_REPORTE_PLU -> procesarReporte((OperationWhitFooter) operacion);
        }
    }

    private void procesarTVOID(OperacionTVOID operacion) {
        procesarDevoluciones(operacion);
        getCajero(operacion.getCajero()).increseTVoid();
    }

    private void procesarREFUND(OperacionREFUND operacion) {
        procesarDevoluciones(operacion);
        getCajero(operacion.getCajero()).increseRefund();
    }

    private void procesarDevoluciones(OperationConProducto operacion) {
        operacion.getPagos().forEach(pago -> ProcesarOperacionUtil.addPago(formasPago, pago));
        operacion.getProductos().forEach(producto -> ProcesarOperacionUtil.addProductoVenta(productos, producto));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(productos, correccion));
        operacion.getProductos().forEach(ventaProducto -> ProcesarOperacionUtil.addProductoVenta(devoluciones, ventaProducto));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(devoluciones, correccion));
        if (!operacion.getOperacionCuadrada())
            operacionesDescuadradas.add(operacion);

        getCajero(operacion.getCajero()).increseCantidadClientes();
        getCajero(operacion.getCajero()).addVentas(operacion.getPagos());
        getCajero(operacion.getCajero()).increseCorrecciones(operacion.getCorrecciones());
        getCajero(operacion.getCajero()).increseProductos(operacion.getProductos());
    }

    private void procesarVenta(OperacionVenta operacion) {
        operacion.getProductos().forEach(ventaProducto -> ProcesarOperacionUtil.addProductoVenta(productos, ventaProducto));
        operacion.getPagos().forEach(pago -> ProcesarOperacionUtil.addPago(formasPago, pago));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(productos, correccion));
        if (!operacion.getOperacionCuadrada())
            operacionesDescuadradas.add(operacion);

        getCajero(operacion.getCajero()).increseCantidadClientes();
        getCajero(operacion.getCajero()).addVentas(operacion.getPagos());
        getCajero(operacion.getCajero()).increseCorrecciones(operacion.getCorrecciones());
        getCajero(operacion.getCajero()).increseProductos(operacion.getProductos());

    }

    private void procesarReporte(OperationWhitFooter operacion) {
        getCajero(operacion.getCajero()).increseReportes();
    }

    private void procesarPropina(OperacionPropina operacion) {
        ProcesarOperacionUtil.addPago(propinas, operacion.getPago().clone());
        getCajero(operacion.getCajero()).incresePropina(operacion.getPago());
    }

    private Cajero getCajero(String code) {
        Cajero cajero = cajeros.stream()
                .filter(c -> c.getCodigo().equals(code))
                .findFirst()
                .orElse(null);
        if (cajero == null) {
            cajero = Cajero.builder()
                    .codigo(code)
                    .ventas(new ArrayList<>())
                    .build();
            cajeros.add(cajero);
        }
        return cajero;
    }

    public void deleteProductosCountCero() {
        List<VentaProducto> productosToDelete = productos.stream()
                .filter(ventaProducto -> ventaProducto.getCantidad() == 0)
                .toList();
        productos.removeAll(productosToDelete);
        productosToDelete = devoluciones.stream()
                .filter(ventaProducto -> ventaProducto.getCantidad() == 0)
                .toList();
        devoluciones.removeAll(productosToDelete);
    }
}
