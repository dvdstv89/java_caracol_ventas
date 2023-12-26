package pvc.caracol.mistral.mapper;

import pvc.caracol.common.utils.DateUtil;
import pvc.caracol.mistral.model.CintaAuditora;
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
                .fechaCreacion(DateUtil.convertirToLocalDate(rs.getDate("FECHA")))
                .fechaProcesado(DateUtil.convertirToLocalDate(rs.getDate("FECHA_RCV")))
                .fichero(rs.getBytes("INFOAUX"))
                .build();
        return cintaAuditora;
    }
}
