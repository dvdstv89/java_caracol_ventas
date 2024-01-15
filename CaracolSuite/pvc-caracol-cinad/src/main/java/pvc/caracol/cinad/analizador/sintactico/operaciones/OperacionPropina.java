package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.models.Pago;
import pvc.caracol.cinad.utils.ProcesarOperacionUtil;

import java.util.List;

@Getter
@Setter
public class OperacionPropina extends OperationWhitFooter {
    private List<Pago> propinas;

    public OperacionPropina(List<Token> tokens) {
        super(TipoOperacion.PROPINA, tokens);
        setInfoPago();
    }

    private void setInfoPago() {
        String formaPago = findTokenByTokenKind(TokenKind.tk_FORMAS_PAGO).getLexema();
        String monto = findTokenByTokenKind(TokenKind.tk_LITERAL_DOUBLE).getLexema();
        Pago pago = new Pago(formaPago, monto);
        ProcesarOperacionUtil.addPago(propinas, pago);
    }
}