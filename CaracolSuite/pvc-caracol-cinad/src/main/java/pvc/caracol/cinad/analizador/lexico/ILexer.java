package pvc.caracol.cinad.analizador.lexico;

import pvc.caracol.cinad.analizador.lexico.tokens.Token;

import java.util.List;

public interface ILexer {
    Token nextToken();

    List<Token> nextOperationSingleTokens();
}
