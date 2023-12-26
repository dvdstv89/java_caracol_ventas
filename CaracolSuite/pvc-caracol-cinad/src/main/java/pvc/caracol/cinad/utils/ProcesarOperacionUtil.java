package pvc.caracol.cinad.utils;

import pvc.caracol.cinad.analizador.sintactico.models.CorreccionVenta;
import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.models.Pago;

import java.util.List;

public class ProcesarOperacionUtil {

    public static void addProductoVenta(List<VentaProducto> productos, VentaProducto productoOperacion) {
        VentaProducto ventaProductoEncontrada = productos.stream()
                .filter(pv -> pv.getProducto().equals(productoOperacion.getProducto()))
                .findFirst()
                .orElse(null);
        if (ventaProductoEncontrada != null) {
            ventaProductoEncontrada.addCantidad(productoOperacion.getCantidad());
        } else {
            productos.add(productoOperacion.clone());
        }
    }

    public static void addPago(List<Pago> pagos, Pago pago) {
        Pago pagoEncontrado = pagos.stream()
                .filter(pg -> pg.getFormaPago().equals(pago.getFormaPago()))
                .findFirst()
                .orElse(null);
        if (pagoEncontrado != null) {
            pagoEncontrado.addMonto(pago.getPagado());
        } else {
            pagos.add(pago.clone());
        }
    }

    public static void addProductoCorreccion( List<VentaProducto> productos, CorreccionVenta correccionVenta) {
        VentaProducto correccionVentaEncontrada = productos.stream()
                .filter(pv -> pv.getProducto().equals(correccionVenta.getProducto()))
                .findFirst()
                .orElse(null);
        if (correccionVentaEncontrada != null) {
            correccionVentaEncontrada.addCantidad(correccionVenta.getCantidad());
        }
    }
}
