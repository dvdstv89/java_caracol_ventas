package pvc.caracol.cinad.analizador.sintactico.operaciones;

import lombok.Getter;
import lombok.Setter;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OperacionMantenimientoPLU extends Operation {
    public OperacionMantenimientoPLU(List<Token> tokens) {
        super(TipoOperacion.MANTENIMIENTO_PLU_MISTRAL, tokens);
    }

    @Override
    public String getCodigoDiaOperacion() {
        return null;
    }

    @Override
    public Integer getIdOperacion() {
        return null;
    }

    @Override
    public LocalDateTime getFecha() {
        return null;
    }

    @Override
    public String getCajero() {
        return null;
    }
}