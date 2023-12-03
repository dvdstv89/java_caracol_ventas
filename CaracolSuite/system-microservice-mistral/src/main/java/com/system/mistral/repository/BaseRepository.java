package com.system.mistral.repository;

import com.system.mistral.service.IJdbcTemplateService;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepository {
    private final IJdbcTemplateService jdbcTemplateService;

    protected BaseRepository(IJdbcTemplateService databaseConfigProvider) {
        this.jdbcTemplateService = databaseConfigProvider;
    }

    protected JdbcTemplate createJdbcTemplate(String centroGestion) {
        return jdbcTemplateService.getJdbcTemplate(centroGestion);
    }

    protected String buildQueryWithParameters(String baseQuery, Object[] parameters) {
        String queryWithParameters = baseQuery;

        for (Object parameter : parameters) {
            if (parameter instanceof String) {
                queryWithParameters = queryWithParameters.replaceFirst("\\?", "'" + parameter + "'");
            } else {
                queryWithParameters = queryWithParameters.replaceFirst("\\?", parameter.toString());
            }
        }
        return queryWithParameters;
    }
}