package pvc.caracol.cinad.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;
import pvc.caracol.cinad.analizador.sintactico.operaciones.IOperacion;
import pvc.caracol.cinad.analizador.sintactico.operaciones.OperacionMantenimientoPLU;
import pvc.caracol.cinad.utils.PodarTextUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class CintaAuditoraElectronica {
    private String nombrePuntoVenta;
    private LocalDateTime fechaHaladoVenta;
    private List<DiaOperacion> diaOperacions;
    @JsonIgnore
    private List<IOperacion> operaciones;

    public CintaAuditoraElectronica() {
        operaciones = new ArrayList<>();
        diaOperacions = new ArrayList<>();
    }

    public void setFechaHaladoVenta(String fechaString, String hora) {
        try {
            String[] fechaSplit = fechaString.split(CharType.GUION.getCharacterAsString());
            String[] diaSplit = fechaSplit[0].split(CharType.SPACE.getCharacterAsString());
            StringBuilder fechaFormated = new StringBuilder(diaSplit[diaSplit.length - 1].trim())
                    .append(CharType.GUION.getCharacterAsString())
                    .append(fechaSplit[1].trim())
                    .append(CharType.GUION.getCharacterAsString())
                    .append(fechaSplit[2].trim())
                    .append(CharType.SPACE.getCharacterAsString())
                    .append(hora);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy h:mm:ss a");
            this.fechaHaladoVenta = LocalDateTime.parse(fechaFormated.toString(), formatter);
        } catch (Exception e) {
            String error = e.toString();
        }
    }

    public void setNombrePuntoVenta(String nombre) {
        this.nombrePuntoVenta = PodarTextUtil.podar(nombre);
    }

    public void addOperation(IOperacion operation) {
        if ((operation instanceof OperacionMantenimientoPLU))
            return;

        operaciones.add(operation);
        DiaOperacion diaOperacion = diaOperacions.stream()
                .filter(op -> op.getCodigoDiaOperacion().equals(operation.getCodigoDiaOperacion()))
                .findFirst().orElse(null);
        if(diaOperacion != null){
            diaOperacion.addOperation(operation);
        }
        else {
            diaOperacion = new DiaOperacion();
            diaOperacion.setCodigoDiaOperacion(operation.getCodigoDiaOperacion());
            diaOperacion.setFecha(operation.getFecha().toLocalDate());
            diaOperacion.addOperation(operation);
            diaOperacions.add(diaOperacion);
        }
    }

    public void depurarDiasOperaciones(){
        if(diaOperacions.get(diaOperacions.size()-1).getCantidadOperaciones()<3){
            diaOperacions.remove(diaOperacions.size()-1);
        }
    }
}
