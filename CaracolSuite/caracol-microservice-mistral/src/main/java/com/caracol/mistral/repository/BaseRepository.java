package com.caracol.mistral.repository;

import com.caracol.mistral.model.DataBaseInfo;
import com.caracol.mistral.service.IDataBaseInfoService;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class BaseRepository {
    private final IDataBaseInfoService databaseConfigProvider;

    protected BaseRepository(IDataBaseInfoService databaseConfigProvider) {
        this.databaseConfigProvider = databaseConfigProvider;
    }

    protected JdbcTemplate createJdbcTemplate(String centroGestion) {
        DataBaseInfo dataBaseInfo = databaseConfigProvider.getDatabaseInfo(centroGestion);
        String databaseUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;trustServerCertificate=true",
                dataBaseInfo.getHost(), dataBaseInfo.getPort(), dataBaseInfo.getName());

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url(databaseUrl)
                .username(dataBaseInfo.getUsername())
                .password(dataBaseInfo.getPassword())
                .build();
        return new JdbcTemplate(dataSource);
    }
}