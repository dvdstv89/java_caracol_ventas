package pvc.caracol.mistral.repository;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.TiendaDto;
import pvc.caracol.mistral.mapper.CajaMapper;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;
import pvc.caracol.mistral.service.interfaces.IJdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CajaRepository extends BaseRepository implements ICajaRepository {

    @Autowired
    public CajaRepository(IJdbcTemplateService dataBaseInfoService) {
        super(dataBaseInfoService);
    }

    @Override
    public List<Caja> getCajasActivasByCentroGestion(Integer idCentroGestion) throws NotFoundException, FeignClientException {
        StringBuilder query = new StringBuilder("SELECT * ")
                .append("FROM TBL_CAJAS ")
                .append("WHERE ACTIVA = 1");
        return createJdbcTemplate(idCentroGestion)
                .query(query.toString(), new CajaMapper(idCentroGestion));
    }

    @Override
    public List<Caja> getCajasActivasByTienda(TiendaDto tiendaDto) throws FeignClientException, NotFoundException {
        StringBuilder stringBuilder = new StringBuilder("SELECT * ")
                .append("FROM TBL_CAJAS ")
                .append("WHERE ACTIVA = 1")
                .append(" AND CODCOMER = ?");

        Object[] queryParams = {
                tiendaDto.getCodeTienda()
        };

        String query = buildQueryWithParameters(stringBuilder.toString(), queryParams);
        return createJdbcTemplate(tiendaDto.getIdCentroGestion())
                .query(query.toString(), new CajaMapper(tiendaDto.getIdCentroGestion()));
    }
}
