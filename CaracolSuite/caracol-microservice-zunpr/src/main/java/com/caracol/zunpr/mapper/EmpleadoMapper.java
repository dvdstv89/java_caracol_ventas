package com.caracol.zunpr.mapper;

import com.caracol.zunpr.enums.Genero;
import com.caracol.zunpr.enums.Raza;
import com.caracol.zunpr.model.Direccion;
import com.caracol.zunpr.model.Empleado;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoMapper implements RowMapper<Empleado> {
    @Override
    public Empleado mapRow(ResultSet rs, int rowNum) throws SQLException {
        Empleado empleado = Empleado.builder()
                .id(rs.getString("id"))
                .nombre(rs.getString("nombre"))
                .primerApellido(rs.getString("primerApellido"))
                .segundoApellido(rs.getString("segundoApellido"))
                .ci(rs.getString("ci"))
                .genero(Genero.fromSexo(rs.getString("sexo")))
                .raza(Raza.fromColorPiel(rs.getString("color_piel")))
                .telefono(rs.getString("telefono"))
                .fechaNacimiento(rs.getDate("fechaNacimiento"))
                .unidadOrganizacional(rs.getString("unidadOrganizacional"))
                .salario(rs.getFloat("salario"))
                .grupoSalarial(rs.getString("grupoSalarial"))//todo quitarle el espacio final
                .cargo(rs.getString("cargo"))
                .direccion(
                        Direccion.builder()
                                .direccion(rs.getString("direccion"))
                                .municipio(rs.getString("municipio"))
                                .provincia(rs.getString("provincia"))
                                .build()
                )
                .build();
        return empleado;
    }
}
