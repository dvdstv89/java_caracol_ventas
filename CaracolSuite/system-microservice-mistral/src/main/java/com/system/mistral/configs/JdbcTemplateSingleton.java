package com.system.mistral.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class JdbcTemplateSingleton {
    private Map<String, JdbcTemplate> jdbcTemplateMap  = new ConcurrentHashMap<>();

    public void addJdbcTemplate(String centroGestion, JdbcTemplate jdbcTemplate) {
        jdbcTemplateMap .put(centroGestion, jdbcTemplate);
    }

    public JdbcTemplate  getJdbcTemplate(String centroGestion) {
        return jdbcTemplateMap .get(centroGestion);
    }
}
