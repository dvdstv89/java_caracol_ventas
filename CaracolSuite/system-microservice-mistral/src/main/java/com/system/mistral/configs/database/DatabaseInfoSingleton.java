package com.system.mistral.configs.database;

import com.system.mistral.model.DataBaseInfo;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseInfoSingleton {
    private Map<String, DataBaseInfo> databaseInfoMap = new HashMap<>();

    public void addDatabaseInfo(String centroGestion, DataBaseInfo databaseInfo) {
        databaseInfoMap.put(centroGestion, databaseInfo);
    }

    public DataBaseInfo getDatabaseInfo(String centroGestion) {
        return databaseInfoMap.get(centroGestion);
    }
}
