package com.caracol.zunpr.dtos;

import com.caracol.zunpr.enums.Genero;
import com.caracol.zunpr.enums.Raza;
import com.caracol.zunpr.model.Direccion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmpleadoDTO {
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
