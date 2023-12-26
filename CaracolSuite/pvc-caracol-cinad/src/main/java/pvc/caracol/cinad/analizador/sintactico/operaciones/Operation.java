package pvc.caracol.cinad.analizador.sintactico.operaciones;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Operation implements IOperacion {
    protected TipoOperacion tipoOperacion;
    //@JsonIgnore
    protected List<Token> tokens;

    protected Operation(TipoOperacion tipoOperacion, List<Token> tokens) {
        this.tipoOperacion = tipoOperacion;
        this.tokens = new ArrayList<>(tokens);
    }

    protected Token findTokenByTokenKind(TokenKind tokenKind) {
        return tokens.stream().filter(toke -> toke.match(tokenKind)).findFirst().orElse(null);
    }
}
