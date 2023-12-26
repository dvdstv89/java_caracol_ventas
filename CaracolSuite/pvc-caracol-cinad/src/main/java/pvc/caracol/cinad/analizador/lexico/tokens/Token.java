package pvc.caracol.cinad.analizador.lexico.tokens;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;

@Data
@Builder
public class Token {
    public static final String DOUBLE_SPACE = " {2}";
    private TokenKind tokenKind;
    private String lexema;
    private Integer lineaUbicacion;
    private Integer longitud;

    public Boolean match(TokenKind tokenKind) {
        try {
            return this.tokenKind.equals(tokenKind);
        } catch (Exception e) {
            return false;
        }
    }

    @JsonIgnore
    public Character getFistCharacter() {
        return lexema.charAt(0);
    }

    public void setTokenKind(TokenKind tokenKind){
        this.tokenKind = tokenKind;
        if (match(TokenKind.tk_CODIGO_PRODUCTO) && !lexema.isEmpty()){
            lexema = lexema.substring(1);
        }
    }
}
