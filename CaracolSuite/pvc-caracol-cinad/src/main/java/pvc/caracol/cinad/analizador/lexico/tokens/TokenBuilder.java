package pvc.caracol.cinad.analizador.lexico.tokens;

import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.lexico.tokens.utils.TokenUtils;
import pvc.caracol.cinad.analizador.lexema.Lexema;

public class TokenBuilder {
    public static Token tokenUndefined(Lexema lexema) {
        Token token = Token.builder()
                .lexema(lexema.getText())
                .lineaUbicacion(lexema.getLine())
                .longitud(lexema.getText().length())
                .build();
        token.setTokenKind(TokenUtils.tryToChangeTokenKind(token.getLexema()));
        return token;
    }

    public static Token tokenNombreProducto(String lexema, int currentLine) {
        return Token.builder()
                .tokenKind(TokenKind.tk_NOMBRE_PRODUCTO)
                .lexema(lexema)
                .lineaUbicacion(currentLine)
                .longitud(lexema.length())
                .build();
    }

    public static Token tokenError(String lexema, int currentLine) {
        return Token.builder()
                .tokenKind(TokenKind.tk_ERROR)
                .lexema(lexema)
                .lineaUbicacion(currentLine)
                .longitud(lexema.length())
                .build();
    }

    public static Token tokenEOT(int currentLine) {
        return Token.builder()
                .tokenKind(TokenKind.tk_EOT)
                .lexema("EOT")
                .lineaUbicacion(currentLine)
                .longitud(3)
                .build();
    }

    public static Token tokenNormal(TokenKind tokenKind, String lexema, int currentLine) {
        return Token.builder()
                .tokenKind(tokenKind)
                .lexema(lexema)
                .lineaUbicacion(currentLine)
                .longitud(lexema.length())
                .build();
    }
}
