package com.system.mistral.mapper;

import com.system.mistral.model.CintaAuditora;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CintaAuditoraMapper implements RowMapper<CintaAuditora> {
    @Override
    public CintaAuditora mapRow(ResultSet rs, int rowNum) throws SQLException {
        CintaAuditora cintaAuditora = CintaAuditora.builder()
                .idCaja(rs.getString("CODCAJA"))
                .codigoRed(rs.getString("CODRED"))
                .codigoAlmacen(rs.getString("CODALMACEN"))
                .fechaCreacion(rs.getDate("FECHA"))
                .fechaProcesado(rs.getDate("FECHA_RCV"))
                .fichero(rs.getBytes("INFOAUX"))
                .build();
        return cintaAuditora;
    }
}
