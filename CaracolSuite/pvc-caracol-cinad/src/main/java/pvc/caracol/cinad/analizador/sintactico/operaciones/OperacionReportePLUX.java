package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.util.List;

@Getter
@Setter
public class OperacionReportePLUX extends OperationWhitFooter {
    public OperacionReportePLUX(List<Token> tokens) {
        super(TipoOperacion.X3_REPORTE_PLU, tokens);
    }
}