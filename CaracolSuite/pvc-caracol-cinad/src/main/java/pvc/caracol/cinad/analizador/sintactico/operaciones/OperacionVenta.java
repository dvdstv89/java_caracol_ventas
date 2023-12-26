package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.models.ProductoOperacion;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.FormaPago;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.models.Pago;

import java.util.List;

@Getter
@Setter
public class OperacionVenta extends OperationConProducto {

    public OperacionVenta(List<Token> tokens) {
        super(TipoOperacion.VENTA, tokens);
    }
}