package pvc.caracol.mistral.repository;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.service.interfaces.IJdbcTemplateService;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepository {
    private final IJdbcTemplateService jdbcTemplateService;

    protected BaseRepository(IJdbcTemplateService databaseConfigProvider) {
        this.jdbcTemplateService = databaseConfigProvider;
    }

    protected JdbcTemplate createJdbcTemplate(String centroGestion) throws NotFoundException {
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