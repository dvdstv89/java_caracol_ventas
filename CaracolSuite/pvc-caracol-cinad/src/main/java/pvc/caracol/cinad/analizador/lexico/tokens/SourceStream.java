package pvc.caracol.cinad.analizador.lexico.tokens;

import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;

import java.io.IOException;
import java.io.StringReader;

@Data
public class SourceStream {
    private static final int END_OF_STREAM = -1;
    private StringReader textReader;
    private Integer currentLine;
    private Character currentChar;

    public SourceStream(String datasource) {
        currentLine = 1;
        textReader = new StringReader(datasource);
    }

    public void consume() {
        try {
            int charRead = textReader.read();
            if (charRead == END_OF_STREAM) {
                currentChar = CharType.FINISH_FILE.getCharacter();
            } else {
                currentChar = (char) charRead;
                if (currentChar == CharType.ENTER.getCharacter()) {
                    currentLine++;
                }
            }

        } catch (IOException e) {
            currentChar = CharType.FINISH_FILE.getCharacter();
        }
    }

    public void consumeIgnoring() {
        while (isIgnorable()) {
            consume();
        }
    }

    public Boolean currentcharIsEqualTo(Character character) {
        return currentChar.equals(character);
    }

    public Boolean isFinalOfToken() {
        try {
            return (currentcharIsEqualTo(CharType.COMILLA.getCharacter()) ||
                    currentcharIsEqualTo(CharType.TAB.getCharacter()) ||
                    currentcharIsEqualTo(CharType.PUNTO_COMA.getCharacter()) ||
                    currentcharIsEqualTo(CharType.ENTER.getCharacter()) ||
                    currentcharIsEqualTo(CharType.RETURN.getCharacter()));
        } catch (Exception ex) {
            return false;
        }
    }

    private Boolean isIgnorable() {
        try {
            return (isDigit() ||
                    currentcharIsEqualTo(CharType.SPACE.getCharacter()) ||
                    currentcharIsEqualTo(CharType.TAB.getCharacter()) ||
                    currentcharIsEqualTo(CharType.RETURN.getCharacter()) ||
                    currentcharIsEqualTo(CharType.ENTER.getCharacter()) ||
                    currentcharIsEqualTo(CharType.PUNTO_COMA.getCharacter()) ||
                    currentcharIsEqualTo(CharType.PUNTO.getCharacter()) ||
                    currentcharIsEqualTo(CharType.GUION.getCharacter()));
        } catch (Exception ex) {
            return false;
        }
    }

    public Boolean isDigit() {
        try {
            return Character.isDigit(currentChar);
        } catch (Exception ex) {
            return false;
        }
    }
}
