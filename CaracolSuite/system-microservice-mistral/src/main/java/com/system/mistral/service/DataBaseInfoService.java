package com.system.mistral.service;

import com.system.mistral.client.IEmpresarialClient;
import com.system.mistral.configs.database.DatabaseInfoSingleton;
import com.system.mistral.model.DataBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInfoService implements IDataBaseInfoService {
    private DatabaseInfoSingleton databaseInfoSingleton;
    private IEmpresarialClient empresarialClient;

    @Autowired
    public DataBaseInfoService(DatabaseInfoSingleton databaseInfoSingleton, IEmpresarialClient empresarialClient) {
        this.databaseInfoSingleton = databaseInfoSingleton;
        this.empresarialClient = empresarialClient;
    }

    @Override
    public DataBaseInfo getDatabaseInfo(String centroGestion) {
        DataBaseInfo databaseInfo = databaseInfoSingleton.getDatabaseInfo(centroGestion);
        if (databaseInfo == null) {
            databaseInfo = empresarialClient.findBaseDatosMistralByCodeCentroGestion(centroGestion);
            databaseInfoSingleton.addDatabaseInfo(centroGestion, databaseInfo);
        }
        return databaseInfo;
    }
}
