package pvc.caracol.cinad.analizador.sintactico.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoCorreccion;

@Data
@Getter
@EqualsAndHashCode(callSuper = true)
public class CorreccionVenta extends ProductoOperacion{
    private final TipoCorreccion tipoCorreccion;
    public CorreccionVenta(TipoCorreccion tipoCorreccion,String codigo, String nombre) {
        super(codigo, nombre);
        this.tipoCorreccion = tipoCorreccion;
    }
}
