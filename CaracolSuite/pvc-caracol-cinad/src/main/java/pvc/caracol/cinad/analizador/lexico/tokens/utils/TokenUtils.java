package pvc.caracol.cinad.analizador.lexico.tokens.utils;

import pvc.caracol.cinad.Application;
import pvc.caracol.cinad.analizador.lexema.Lexema;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.TokenBuilder;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.PalabrasReservadas;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TokenUtils {
    public static TokenKind tryToChangeTokenKind(String lexema) {
        TokenKind tokenKind = TokenUtils.convertirPalabraEnTipoToken(lexema.toString());
        return (tokenKind.equals(TokenKind.tk_CADENA_TEXTO))
                ? PalabrasReservadas.getToken(lexema.toString())
                : tokenKind;
    }

    private static TokenKind convertirPalabraEnTipoToken(String word) {
        if (isTokenLiteralEntero(word))
            return TokenKind.tk_LITERAL_ENTERO;

        if (isTokenLiteralDouble(word))
            return TokenKind.tk_LITERAL_DOUBLE;

        if (isTokenReportCode(word))
            return TokenKind.tk_CODIGO_REPORTE;

        if (isTokenNameCajero(word))
            return TokenKind.tk_NOMBRE_CAJERO;

        if (isTokenProductCode(word))
            return TokenKind.tk_CODIGO_PRODUCTO;

        if (isTokenDate(word))
            return TokenKind.tk_FECHA;

        if (isTokenFechaSimple(word))
            return TokenKind.Tk_FECHA_SIMPLE;

        if (isTokenHora(word))
            return TokenKind.tk_HORA;

        return TokenKind.tk_CADENA_TEXTO;
    }

    private static boolean isTokenLiteralEntero(String word) {
        return (WordsUtil.isInteger(word));
    }

    private static boolean isTokenLiteralDouble(String word) {
        return (WordsUtil.isDouble(word));
    }

    private static boolean isTokenNameCajero(String word) {
        return (isCharacterEqualInPosition(word, 0, CharType.ARROBA));
    }

    private static Boolean isCharacterEqualInPosition(String word, Integer position, CharType charType) {
        try {
            return word.charAt(position) == charType.getCharacter();
        } catch (Exception ex) {
            return false;
        }
    }

    private static boolean isTokenReportCode(String word) {
        return ((isCharacterEqualInPosition(word, 0, CharType.X)
                || isCharacterEqualInPosition(word, 0, CharType.Z))
                && word.length() == Application.COUNT_CHARACTERS_REPORT_CODE);
    }

    private static boolean isTokenProductCode(String word) {
        return ((word.charAt(0) == CharType.HASHTAG.getCharacter())
                && word.length() >= Application.MIN_COUNT_DIGIT_PRODUCT_CODE
                && WordsUtil.isAllDigits(word.substring(1)));
    }

    private static boolean isTokenDate(String word) {
        return (isCharacterEqualInPosition(word, word.length() - 1, CharType.A)
                || isCharacterEqualInPosition(word, word.length() - 1, CharType.P))
                && word.length() > 4
                && isCharacterEqualInPosition(word, word.length() - 4, CharType.TWO_POINTS);
    }

    private static boolean isTokenHora(String word) {
        String[] lexemas = word.split(CharType.SPACE.getCharacterAsString());
        return (lexemas.length == 2 && (lexemas[1].equals("AM") || lexemas[1].equals("PM")));
    }

    private static boolean isTokenFechaSimple(String word) {
        String[] lexemas = word.split(CharType.GUION.getCharacterAsString());
        return lexemas.length == 3 && !lexemas[0].equals(CharType.PARENTISIS.getCharacterAsString()) && !lexemas[0].isEmpty();
    }

    public static List<Token> splitByDoubleSpace(Token token) {
        List<Lexema> lexemesDivides = Arrays.stream(token.getLexema()
                        .split(Token.DOUBLE_SPACE)).sequential()
                .map(lexeme -> new Lexema(lexeme, token.getLineaUbicacion())
                )
                .toList();

        if (lexemesDivides.size() > 1) {
            return lexemesDivides.stream()
                    .filter(lexema -> !lexema.getText().isEmpty())
                    .map(TokenBuilder::tokenUndefined)
                    .toList();
        }
        return null;
    }

   /* public static List<Token> trySplitTokenAgain(Token token) {
        List<Token> tokens = new ArrayList<>();
        if (token.match(TokenKind.tk_CADENA_TEXTO)) {
            String[] lexemesDivides = token.getLexema().split(CharType.TWO_POINTS.getCharacterAsString());
            if (lexemesDivides.length == 2) {
                // tarjeta de credito
                token.setTokenKind(TokenKind.tk_NUMERO_TC);
                token.setLexema(lexemesDivides[0]);
                tokens.add(token);
                tokens.add(TokenBuilder.tokenNormal(TokenKind.tk_CODE_TC, lexemesDivides[1], token.getLineaUbicacion()));
            } else {
                lexemesDivides = token.getLexema().split(CharType.SPACE.getCharacterAsString());
                if (lexemesDivides.length == 2) {
                    token.setTokenKind(TokenKind.tk_LITERAL_ENTERO);
                    token.setLexema(lexemesDivides[0]);
                    tokens.add(token);
                    tokens.add(TokenBuilder.tokenUndefined(lexemesDivides[1], token.getLineaUbicacion()));
                }
            }

        }
        return tokens;
    }*/

    public static List<Token> splitBySpaceNombreProducto(Token token) {
        List<Token> tokens = new ArrayList<>();
        String[] lexemesDivides = token.getLexema().split(CharType.SPACE.getCharacterAsString());
        if (WordsUtil.isAllDigits(lexemesDivides[0])) {
            token.setTokenKind(TokenKind.tk_CANTIDAD);
            token.setLexema(lexemesDivides[0]);
            tokens.add(token);

            String nombreProducto = String.join(" ", Arrays.copyOfRange(lexemesDivides, 1, lexemesDivides.length - 1));
            Token tokenProducto = TokenBuilder.tokenNombreProducto(nombreProducto, token.getLineaUbicacion());
            tokens.add(tokenProducto);
        }
        return tokens;
    }
}
