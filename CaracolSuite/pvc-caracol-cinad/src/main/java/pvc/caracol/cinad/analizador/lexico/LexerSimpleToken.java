package pvc.caracol.cinad.analizador.lexico;

import lombok.Data;
import pvc.caracol.cinad.analizador.lexema.ILexemaFactory;
import pvc.caracol.cinad.analizador.lexema.LexemaFactory;
import pvc.caracol.cinad.analizador.lexico.tokens.SourceStream;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.TokenBuilder;
import pvc.caracol.cinad.analizador.lexema.Lexema;

@Data
public class LexerSimpleToken implements ILexerSimpleToken {
    private ILexemaFactory lexemaFactory;

    public LexerSimpleToken(SourceStream sourceStream) {
        lexemaFactory = new LexemaFactory(sourceStream);
    }
    @Override
    public Token getToken() {
        Lexema lexema = getNextNonEmptyLexema();
        if (lexema == null) {
            return TokenBuilder.tokenEOT(lexemaFactory.getCurrentLine());
        }
        return TokenBuilder.tokenUndefined(lexema);
    }

    private Lexema getNextNonEmptyLexema() {
        Lexema lexema = lexemaFactory.getToken();
        while (lexema != null && lexema.getText().isEmpty()) {
            lexema = lexemaFactory.getToken();
        }
        return lexema;
    }
}
