package pvc.caracol.cinad.analizador.lexema;

import lombok.Builder;
import lombok.Data;

@Data
public class Lexema {
    private String text;
    private int line;
    public Lexema(String text, int line){
        this.line = line;
        this.text = text.trim();
    }
}