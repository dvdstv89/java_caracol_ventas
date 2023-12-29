package pvc.caracol.rrhh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Trabajador {
    private String id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String ci;
    private String genero;
    private String raza;
    private String telefono;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    private int edad;
    private String unidadOrganizacional;
    private Float salario;
    private String grupoSalarial;
    private String cargo;
}
