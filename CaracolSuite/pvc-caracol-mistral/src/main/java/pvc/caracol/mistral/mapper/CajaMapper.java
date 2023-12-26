package pvc.caracol.mistral.mapper;

import pvc.caracol.mistral.enums.ModeloCaja;
import pvc.caracol.mistral.model.Caja;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CajaMapper implements RowMapper<Caja> {
    @Override
    public Caja mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Caja.builder()
                .id(rs.getString("CODCAJA"))
                .codigoRed(rs.getString("CODRED"))
                .centroCosto(rs.getString("CCOSTO"))
                .numeroCaja(rs.getString("ID"))
                .descripcion(rs.getString("DESCRIPCION"))
                .codigoComercial(rs.getString("CODCOMER"))
                .mlc(rs.getBoolean("MLC"))
                .modelo(ModeloCaja.fromSerie(rs.getString("TIPOCAJA")))
                .build();
    }
}
