package pvc.caracol.mistral.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.mapper.CintaAuditoraMapper;
import pvc.caracol.mistral.model.CintaAuditora;
import pvc.caracol.mistral.repository.interfaces.ICintaAuditoraRepository;
import pvc.caracol.mistral.service.interfaces.IJdbcTemplateService;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class CintaAuditoraRepository extends BaseRepository implements ICintaAuditoraRepository {

    @Autowired
    public CintaAuditoraRepository(IJdbcTemplateService dataBaseInfoService) {
        super(dataBaseInfoService);
    }

    public List<CintaAuditora> getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException, FeignClientException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        StringBuilder stringBuilder = new StringBuilder("SELECT * FROM TPV_CAJA_OPS ")
                .append("WHERE CODCAJA = ? ")
                .append("and CODRED = ? ")
                .append("and INFOAUX IS NOT NULL ")
                .append("and FECHA >= ? ")
                .append("and FECHA <= ? ")
                .append("order by FECHA asc");

        Object[] queryParams = {
                cajaRegistradora.getIdCaja(),
                cajaRegistradora.getCodigoRed(),
                cajaRegistradora.getFechaInicio().format(dateFormat),
                dateFormat.format(cajaRegistradora.getFechaFin())
        };

        String query = buildQueryWithParameters(stringBuilder.toString(), queryParams);

        return createJdbcTemplate(cajaRegistradora.getIdCentroGestion())
                .query(query, new CintaAuditoraMapper());
    }
}
