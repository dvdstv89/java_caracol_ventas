package pvc.caracol.cinad.analizador.lexico.tokens.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CharType {
    ENTER('\n'),
    FINISH_FILE('\0'),
    SPACE(' '),
    TAB('\t'),
    RETURN('\r'),
    PUNTO_COMA(';'),
    PUNTO('.'),
    GUION('-'),
    PARENTISIS('('),
    ARROBA('@'),
    TWO_POINTS(':'),
    UNDERLINE('_'),
    Z('Z'),
    X('X'),
    N('N'),
    A('A'),
    P('P'),
    COMILLA('"'),
    HASHTAG('#'),
    ASTERISCO('*');

    private final char character;
    CharType(Character character){
        this.character = character;
    }
    public Character getCharacter() {
        return character;
    }

    public String getCharacterAsString() {
        return String.valueOf(character);
    }

    private static final Map<Character, CharType> serieToModelMap = Arrays
            .stream(values())
            .collect(Collectors.toMap(CharType::getCharacter, Function.identity()));

    public static CharType fromCharacter(char character) {
        return serieToModelMap.get(character);
    }
}
