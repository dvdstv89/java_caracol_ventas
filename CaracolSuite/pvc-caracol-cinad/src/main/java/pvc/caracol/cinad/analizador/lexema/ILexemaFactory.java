package pvc.caracol.cinad.analizador.lexema;

public interface ILexemaFactory {
    Lexema getToken();
    int getCurrentLine();
}
