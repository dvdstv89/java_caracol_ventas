package com.system.zunpr.dtos;

import com.system.zunpr.enums.Genero;
import com.system.zunpr.enums.Raza;
import com.system.zunpr.model.Direccion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    private Date fechaNacimiento;
    private String unidadOrganizacional;
    private Float salario;
    private String grupoSalarial;
    private String cargo;
    private Direccion direccion;
}
