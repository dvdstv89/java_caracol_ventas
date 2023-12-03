package com.system.mistral.service;

import com.system.mistral.client.IEmpresarialClient;
import com.system.mistral.configs.JdbcTemplateSingleton;
import com.system.mistral.model.DataBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JDBCTemplateService implements IJdbcTemplateService {
    private JdbcTemplateSingleton databaseInfoSingleton;
    private IEmpresarialClient empresarialClient;

    @Autowired
    public JDBCTemplateService(JdbcTemplateSingleton databaseInfoSingleton, IEmpresarialClient empresarialClient) {
        this.databaseInfoSingleton = databaseInfoSingleton;
        this.empresarialClient = empresarialClient;
    }

    @Override
    public JdbcTemplate getJdbcTemplate(String centroGestion) {
        JdbcTemplate jdbcTemplate = databaseInfoSingleton.getJdbcTemplate(centroGestion);
        if (jdbcTemplate == null) {
            jdbcTemplate = createAndCacheJdbcTemplate(centroGestion);
        }
        return jdbcTemplate;
    }

    private JdbcTemplate createAndCacheJdbcTemplate(String centroGestion) {
        DataBaseInfo databaseInfo = empresarialClient.findBaseDatosMistralByCodeCentroGestion(centroGestion);

        String databaseUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;trustServerCertificate=true",
                databaseInfo.getHost(), databaseInfo.getPort(), databaseInfo.getName());

        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .url(databaseUrl)
                .username(databaseInfo.getUsername())
                .password(databaseInfo.getPassword())
                .build();

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        databaseInfoSingleton.addJdbcTemplate(centroGestion, jdbcTemplate);
        return jdbcTemplate;
    }
}
