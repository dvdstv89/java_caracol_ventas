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
    // @JsonIgnore
    private List<IOperacion> operaciones;
    private List<Pago> formasPago;
    private List<Pago> propinas;
    private List<VentaProducto> productos;
    private List<VentaProducto> devoluciones;
    private List<Cajero> cajeros;
    @JsonIgnore
    private int cantidadOperaciones;

    public DiaOperacion() {
        operaciones = new ArrayList<>();
        formasPago = new ArrayList<>();
        productos = new ArrayList<>();
        cajeros = new ArrayList<>();
        propinas = new ArrayList<>();
        devoluciones = new ArrayList<>();
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
        procesarDevoluciones((OperationConProducto) operacion);
    }

    private void procesarREFUND(OperacionREFUND operacion) {
        procesarDevoluciones((OperationConProducto) operacion);
    }

    private void procesarDevoluciones(OperationConProducto operacion) {
        operacion.getPagos().forEach(pago -> ProcesarOperacionUtil.addPago(formasPago, pago));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(productos, correccion));
        operacion.getProductos().forEach(ventaProducto -> ProcesarOperacionUtil.addProductoVenta(devoluciones, ventaProducto));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(devoluciones, correccion));
    }

    private void procesarVenta(OperacionVenta operacion) {
        operacion.getProductos().forEach(ventaProducto -> ProcesarOperacionUtil.addProductoVenta(productos, ventaProducto));
        operacion.getPagos().forEach(pago -> ProcesarOperacionUtil.addPago(formasPago, pago));
        operacion.getCorrecciones().forEach(correccion -> ProcesarOperacionUtil.addProductoCorreccion(productos, correccion));



//correcciones
    }

    private void procesarReporte(OperationWhitFooter operacion) {

    }

    private void procesarPropina(OperacionPropina operacion) {
        ProcesarOperacionUtil.addPago(propinas, operacion.getPago());
    }

    //productos
    //pagos
    //correcciones
    //cajeros


    public double getVentasPorProductos() {
        return productos.stream().mapToDouble(ProductoOperacion::getSaldo).sum();
    }
}
