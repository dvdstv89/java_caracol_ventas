package pvc.caracol.zunpr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import pvc.caracol.zunpr.enums.Genero;
import pvc.caracol.zunpr.enums.Raza;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
public class Empleado {
    private String id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String ci;
    private Genero genero;
    private Raza raza;
    private String telefono;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    public int getEdad() {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears();
    }

    private String unidadOrganizacional;
    private Float salario;
    private String grupoSalarial;
    private String cargo;
    private Direccion direccion;

    public String getGrupoSalarial() {
        return (grupoSalarial != null)
                ? grupoSalarial.trim()
                : "";
    }
}
