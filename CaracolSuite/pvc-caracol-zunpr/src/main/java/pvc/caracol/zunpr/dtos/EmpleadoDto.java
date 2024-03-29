package pvc.caracol.zunpr.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pvc.caracol.zunpr.enums.Genero;
import pvc.caracol.zunpr.enums.Raza;
import pvc.caracol.zunpr.model.Direccion;

import java.time.LocalDate;

@Data
public class EmpleadoDto {
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
    private int edad;
    private String unidadOrganizacional;
    private Float salario;
    private String grupoSalarial;
    private String cargo;
    private Direccion direccion;
}
