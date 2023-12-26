package pvc.caracol.mistral.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.CintaAuditoraRequest;
import pvc.caracol.mistral.mapper.CintaAuditoraMapper;
import pvc.caracol.mistral.model.CintaAuditora;
import pvc.caracol.mistral.repository.interfaces.ICintaAuditoraRepository;
import pvc.caracol.mistral.service.interfaces.IJdbcTemplateService;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Repository
public class CintaAuditoraRepository extends BaseRepository implements ICintaAuditoraRepository {

    @Autowired
    public CintaAuditoraRepository(IJdbcTemplateService dataBaseInfoService) {
        super(dataBaseInfoService);
    }

    public List<CintaAuditora> getCintaAuditora(CintaAuditoraRequest cintaAuditoraRequest) throws NotFoundException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                cintaAuditoraRequest.getFechaInicio().format(dateFormat),
                dateFormat.format(cintaAuditoraRequest.getFechaFin())
        };

        String query = buildQueryWithParameters(stringBuilder.toString(), queryParams);

        return createJdbcTemplate(cintaAuditoraRequest.getCodigoCentroGestion())
                .query(query, new CintaAuditoraMapper());
    }
}
