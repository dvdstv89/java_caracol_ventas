package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.sintactico.models.CorreccionVenta;
import pvc.caracol.cinad.analizador.sintactico.models.ProductoOperacion;
import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.FormaPago;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoCorreccion;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.models.Pago;
import pvc.caracol.cinad.utils.ProcesarOperacionUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Getter
@Setter
public abstract class OperationConProducto extends OperationWhitFooter {
    protected List<Pago> pagos;
    protected List<VentaProducto> productos;
    protected List<CorreccionVenta> correcciones;

    protected OperationConProducto(TipoOperacion tipoOperacion, List<Token> tokens) {
        super(tipoOperacion, tokens);
        this.pagos = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.correcciones = new ArrayList<>();
        prccesarTokens();
    }

    private void prccesarTokens() {
        Queue<Token> tokenQueue = new LinkedList<>(tokens);
        Token tokenActual = tokenQueue.poll();
        while (!tokenQueue.isEmpty()) {
            switch (tokenActual.getTokenKind()) {
                case TokenKind.tk_CODIGO_PRODUCTO -> procesarProducto(tokenQueue, tokenActual);
                case TokenKind.tk_EC -> procesarCorreccion(tokenQueue, TipoCorreccion.EC);
                case TokenKind.tk_VOID -> procesarCorreccion(tokenQueue, TipoCorreccion.VOID);
                case TokenKind.tk_FORMAS_PAGO -> procesarPago(tokenQueue, tokenActual);
            }
            tokenActual = tokenQueue.poll();
        }
    }

    protected void procesarProducto(Queue<Token> tokenQueue, Token tokenActual) {
        String code = tokenActual.getLexema();
        String name = tokenQueue.poll().getLexema();
        VentaProducto productoOperacion = new VentaProducto(code, name);
        ProcesarOperacionUtil.addProductoVenta(productos, productoOperacion);
    }

    protected void procesarCorreccion(Queue<Token> tokenQueue, TipoCorreccion tipoCorreccion) {
        String code = tokenQueue.poll().getLexema();
        String name = tokenQueue.poll().getLexema();
        CorreccionVenta correccionVenta = new CorreccionVenta(tipoCorreccion, code, name);
        correcciones.add(correccionVenta);
    }

    protected void procesarPago(Queue<Token> tokenQueue, Token tokenActual) {
        String formaPago = tokenActual.getLexema();
        String monto = tokenQueue.poll().getLexema();
        Pago pago = new Pago(formaPago, monto);
        ProcesarOperacionUtil.addPago(pagos, pago);
    }
}
