package com.system.mistral.repository;

import com.system.mistral.mapper.CajaMapper;
import com.system.mistral.model.Caja;
import com.system.mistral.service.IJdbcTemplateService;
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
    public List<Caja> getCajasActivas(String centroGestion) {
        StringBuilder query = new StringBuilder("SELECT * ")
                .append("FROM TBL_CAJAS ")
                .append("WHERE ACTIVA = 1");
        return createJdbcTemplate(centroGestion)
                .query(query.toString(), new CajaMapper());
    }
}
