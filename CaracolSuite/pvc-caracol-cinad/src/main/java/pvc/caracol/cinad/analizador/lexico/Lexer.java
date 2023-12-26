package pvc.caracol.cinad.analizador.lexico;

import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.SourceStream;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.lexico.tokens.utils.TokenUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class Lexer implements ILexer {

    private ILexerSimpleToken lexerSimpleToken;
    private Token ultimoTokenOficial;
    private Queue<Token> tokenQueue;

    public Lexer(SourceStream sourceStream) {
        lexerSimpleToken = new LexerSimpleToken(sourceStream);
        tokenQueue = new LinkedList<>();
    }

    @Override
    public Token nextToken() {
        if (!tokenQueue.isEmpty()) {
            Token token = tokenQueue.poll();
            ultimoTokenOficial = token;
            return token;
        }

        Token token = lexerSimpleToken.getToken();
        if (token.match(TokenKind.tk_CADENA_TEXTO)) {
            tryToIdentifyTokenByPreceptor(token);
        }

        if (tryToSplitLexema(token)) {
            token = tokenQueue.poll();
        }

        ultimoTokenOficial = token;
        return token;
    }

    @Override
    public List<Token> nextOperationSingleTokens() {
        List<Token> tokens = new ArrayList<>();
        Token token = nextToken();
        while (!token.match(TokenKind.tk_FOOTER)) {
            tokens.add(token);
            token = nextToken();
        }
        tokens.add(token);
        return tokens;
    }

    private void tryToIdentifyTokenByPreceptor(Token token) {
        switch (ultimoTokenOficial.getTokenKind()) {
            case tk_X -> {
                token.setTokenKind(TokenKind.tk_NOMBRE_PUNTO_VENTA);
            }
            case tk_NOMBRE_PUNTO_VENTA -> {
                token.setTokenKind(TokenKind.tk_MEMORY_USED);
            }
            case tk_FECHA -> {
                token.setTokenKind(TokenKind.tk_FOOTER);
            }
            case tk_Cntd_X -> {
                token.setTokenKind(TokenKind.tk_CODE_FINAL_CINTA);
            }
            case tk_CODIGO_PRODUCTO -> {
                token.setTokenKind(TokenKind.tk_NOMBRE_PRODUCTO);
            }
        }
    }

    private Boolean tryToSplitLexema(Token token) {
        return token.match(TokenKind.tk_CADENA_TEXTO) && procesarTokensDividos(TokenUtils.splitByDoubleSpace(token), token);
    }

    private boolean procesarTokensDividos(List<Token> tokensDivides, Token token) {
        if (tokensDivides != null) {
            List<Token> tokenList = new ArrayList<>(tokenQueue);
            int index = tokenList.indexOf(token);
            if (index != -1) {
                tokenList.remove(index);
                tokenList.addAll(index, tokensDivides);
                tokenQueue = new LinkedList<>(tokenList);
            } else {
                tokenQueue.addAll(tokensDivides);
            }
            return true;
        }
        return false;
    }
}
