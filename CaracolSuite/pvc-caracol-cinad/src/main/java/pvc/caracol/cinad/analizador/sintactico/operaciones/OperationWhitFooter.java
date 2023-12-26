package pvc.caracol.cinad.analizador.sintactico.operaciones;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public abstract class OperationWhitFooter extends Operation {
    protected Integer idOperacion;
    protected String cajero;
    @JsonIgnore
    protected String codigoDiaOperacion;
    protected LocalDateTime fecha;

    protected OperationWhitFooter(TipoOperacion tipoOperacion, List<Token> tokens) {
        super(tipoOperacion, tokens);
        setFechaByToken();
        setInfoByToken();
    }

    private void setFechaByToken() {
        try {
            Token token = findTokenByTokenKind(TokenKind.tk_FECHA);
            if (token != null) {
                String fechaString = token.getLexema();
                String[] fechaSplit = fechaString.split(CharType.GUION.getCharacterAsString());
                int dia = Integer.parseInt(fechaSplit[0].trim());
                int mes = Integer.parseInt(fechaSplit[1].trim());
                fechaSplit = fechaSplit[2].split(CharType.SPACE.getCharacterAsString());
                int anio = Integer.parseInt(fechaSplit[0]) + 2000;
                fechaSplit = fechaSplit[fechaSplit.length - 1].split(CharType.TWO_POINTS.getCharacterAsString());
                int hora = Integer.parseInt(fechaSplit[0].trim());
                int minutos = Integer.parseInt(fechaSplit[1].substring(0, 2));

                if (fechaSplit[1].charAt(2) == CharType.P.getCharacter() && hora < 12) {
                    hora += 12;
                } else if (fechaSplit[1].charAt(2) == CharType.A.getCharacter() && hora == 12) {
                    hora = 0;
                }
                this.fecha = LocalDateTime.of(anio, mes, dia, hora, minutos);
            }
        } catch (Exception e) {
            String error = e.toString();
        }
    }

    private void setInfoByToken() {
        try {
            Token token = findTokenByTokenKind(TokenKind.tk_FOOTER);
            if (token != null) {
                String[] parts = token.getLexema().split(CharType.SPACE.getCharacterAsString());
                idOperacion = Integer.parseInt(parts[0].substring(1));
                cajero = parts[1];
                codigoDiaOperacion = new StringBuilder(fecha.toLocalDate().toString()).append(CharType.UNDERLINE.getCharacterAsString()).append(parts[2]).toString();
            }
        } catch (Exception e) {
            String error = e.toString();
        }
    }
}
