package com.system.mistral.repository;

import com.system.mistral.mapper.CintaAuditoraMapper;
import com.system.mistral.http.input.CintaAuditoraRequest;
import com.system.mistral.model.CintaAuditora;
import com.system.mistral.service.IJdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Repository
public class CintaAuditoraRepository extends BaseRepository implements ICintaAuditoraRepository {

    @Autowired
    public CintaAuditoraRepository(IJdbcTemplateService dataBaseInfoService) {
        super(dataBaseInfoService);
    }

    public List<CintaAuditora> getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedFechaInicio = dateFormat.format(cintaAuditoraRequest.getFechaInicio());
        String formattedFechaFin = dateFormat.format(cintaAuditoraRequest.getFechaFin());

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM TPV_CAJA_OPS ")
                .append("WHERE CODCAJA = ? ")
                .append("and CODRED = ? ")
                .append("and INFOAUX IS NOT NULL ")
                .append("and FECHA >= ? ")
                .append("and FECHA <= ? ")
                .append("order by FECHA asc");

        Object[] queryParams = {
                cintaAuditoraRequest.getIdCajaRegistradora(),
                cintaAuditoraRequest.getCodigoRed(),
                formattedFechaInicio,
                formattedFechaFin
        };

        String query = buildQueryWithParameters(stringBuilder.toString(), queryParams);

        return createJdbcTemplate(cintaAuditoraRequest.getCodigoCentroGestion())
                .query(query, new CintaAuditoraMapper());
    }
}
