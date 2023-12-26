package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.util.List;

@Getter
@Setter
public class OperacionReporteTerminalX extends OperationWhitFooter {
    public OperacionReporteTerminalX(List<Token> tokens) {
        super(TipoOperacion.X1_REPORTE_TERMINAL, tokens);
    }
}