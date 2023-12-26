package pvc.caracol.mistral.repository;

import pvc.caracol.common.exceptions.NotFoundException;
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
    public List<Caja> getCajasActivas(String centroGestion) throws NotFoundException {
        StringBuilder query = new StringBuilder("SELECT * ")
                .append("FROM TBL_CAJAS ")
                .append("WHERE ACTIVA = 1");
        return createJdbcTemplate(centroGestion)
                .query(query.toString(), new CajaMapper());
    }
}
