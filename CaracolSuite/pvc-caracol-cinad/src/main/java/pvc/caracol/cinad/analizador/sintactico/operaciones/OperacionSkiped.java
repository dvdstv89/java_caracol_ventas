package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.util.List;

@Getter
@Setter
public class OperacionSkiped extends OperationWhitFooter {

    public OperacionSkiped(TipoOperacion tipoOperacion,List<Token> tokens) {
        super(tipoOperacion, tokens);
    }
}