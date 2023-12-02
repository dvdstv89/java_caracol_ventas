package com.caracol.mistral.repository;

import com.caracol.mistral.configs.mapper.CajaMapper;
import com.caracol.mistral.model.Caja;
import com.caracol.mistral.service.IDataBaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CajaRepository extends BaseRepository implements ICajaRepository {

    @Autowired
    public CajaRepository(IDataBaseInfoService dataBaseInfoService) {
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
