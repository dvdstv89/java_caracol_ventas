package pvc.caracol.cinad.analizador.sintactico.operaciones;

import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.models.Cajero;

import java.time.LocalDateTime;

public interface IOperacion {
    String getCodigoDiaOperacion();
    Integer getIdOperacion();
    LocalDateTime getFecha();
    String getCajero();
    TipoOperacion getTipoOperacion();
}
