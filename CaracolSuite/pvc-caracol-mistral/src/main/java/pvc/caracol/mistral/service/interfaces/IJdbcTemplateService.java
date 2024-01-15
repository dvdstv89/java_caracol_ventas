package pvc.caracol.mistral.service.interfaces;

import org.springframework.jdbc.core.JdbcTemplate;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;

public interface IJdbcTemplateService {
    JdbcTemplate getJdbcTemplate(Integer idCentroGestion) throws NotFoundException, FeignClientException;
}
