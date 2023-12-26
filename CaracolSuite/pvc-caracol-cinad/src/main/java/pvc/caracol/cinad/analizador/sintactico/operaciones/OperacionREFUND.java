package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.util.List;

@Getter
@Setter
public class OperacionREFUND extends OperationNegativa {
    public OperacionREFUND(List<Token> tokens) {
        super(TipoOperacion.REFUND, tokens);
    }
}
