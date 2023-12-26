package pvc.caracol.cinad.analizador.lexema;

import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.SourceStream;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;

@Data
public class LexemaFactory implements ILexemaFactory {

    private SourceStream sourceStream;

    public LexemaFactory(SourceStream sourceStream) {
        this.sourceStream = sourceStream;
    }

    @Override
    public Lexema getToken() {
        this.sourceStream.consume();
        while (!sourceStream.currentcharIsEqualTo(CharType.FINISH_FILE.getCharacter())) {
            sourceStream.consumeIgnoring();
            if (sourceStream.currentcharIsEqualTo(CharType.COMILLA.getCharacter())) {
                return new Lexema(getSingleLineToken(), sourceStream.getCurrentLine());
            }
        }
        return null;
    }

    @Override
    public int getCurrentLine() {
        return sourceStream.getCurrentLine();
    }

    private String getSingleLineToken() {
        StringBuilder lexeme = new StringBuilder();
        boolean primerCaracter = true;
        int cantidadEspaciosFinales = 0;
        do {
            if (!sourceStream.currentcharIsEqualTo(CharType.SPACE.getCharacter())
                    && !sourceStream.currentcharIsEqualTo(CharType.COMILLA.getCharacter())
                    && primerCaracter) {
                primerCaracter = false;
            }
            if (!primerCaracter) {
                lexeme.append(sourceStream.getCurrentChar());
                cantidadEspaciosFinales = sourceStream.currentcharIsEqualTo(CharType.SPACE.getCharacter())
                        ? cantidadEspaciosFinales + 1
                        : 0;
            }
            sourceStream.consume();
        }
        while (!sourceStream.isFinalOfToken());
        return lexeme.toString();
    }
}
