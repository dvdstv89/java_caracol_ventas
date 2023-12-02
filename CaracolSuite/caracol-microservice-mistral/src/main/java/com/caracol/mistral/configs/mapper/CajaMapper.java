package com.caracol.mistral.configs.mapper;

import com.caracol.mistral.enums.ModeloCaja;
import com.caracol.mistral.model.Caja;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CajaMapper implements RowMapper<Caja> {
    @Override
    public Caja mapRow(ResultSet rs, int rowNum) throws SQLException {
        Caja caja = Caja.builder()
                .id(rs.getString("CODCAJA"))
                .centroCosto(rs.getString("CCOSTO"))
                .numeroCaja(rs.getString("ID"))
                .descripcion(rs.getString("DESCRIPCION"))
                .codigoComercial(rs.getString("CODCOMER"))
                .mlc(rs.getBoolean("MLC"))
                .modelo(ModeloCaja.fromSerie(rs.getString("TIPOCAJA")))
                .build();
        return caja;
    }
}
