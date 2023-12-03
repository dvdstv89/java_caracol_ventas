package com.system.mistral.service;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IJdbcTemplateService {
    JdbcTemplate getJdbcTemplate(String centroGestion);
}
