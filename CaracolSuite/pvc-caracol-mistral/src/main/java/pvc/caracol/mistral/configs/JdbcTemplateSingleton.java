package pvc.caracol.mistral.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class JdbcTemplateSingleton {
    private Map<Integer, JdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<>();

    public void addJdbcTemplate(Integer idCentroGestion, JdbcTemplate jdbcTemplate) {
        jdbcTemplateMap.put(idCentroGestion, jdbcTemplate);
    }

    public JdbcTemplate getJdbcTemplate(Integer idCentroGestion) {
        return jdbcTemplateMap.get(idCentroGestion);
    }
}
