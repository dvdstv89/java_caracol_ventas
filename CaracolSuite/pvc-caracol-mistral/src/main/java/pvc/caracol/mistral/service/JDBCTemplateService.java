package pvc.caracol.mistral.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.client.IEmpresarialClient;
import pvc.caracol.mistral.configs.JdbcTemplateSingleton;
import pvc.caracol.mistral.messages.MessageText;
import pvc.caracol.mistral.model.DataBaseInfo;
import pvc.caracol.mistral.service.interfaces.IJdbcTemplateService;

import javax.sql.DataSource;

@Service
public class JDBCTemplateService implements IJdbcTemplateService {
    private JdbcTemplateSingleton databaseInfoSingleton;
    private final IEmpresarialClient empresarialClient;

    @Autowired
    public JDBCTemplateService(JdbcTemplateSingleton databaseInfoSingleton, IEmpresarialClient empresarialClient) {
        this.databaseInfoSingleton = databaseInfoSingleton;
        this.empresarialClient = empresarialClient;
    }

    @Override
    public JdbcTemplate getJdbcTemplate(String centroGestion) throws NotFoundException, FeignClientException {
        JdbcTemplate jdbcTemplate = databaseInfoSingleton.getJdbcTemplate(centroGestion);
        if (jdbcTemplate == null) {
            jdbcTemplate = createAndCacheJdbcTemplate(centroGestion);
        }
        return jdbcTemplate;
    }

    private JdbcTemplate createAndCacheJdbcTemplate(String centroGestion) throws NotFoundException, FeignClientException {
        try {
            DataBaseInfo databaseInfo = empresarialClient.findBaseDatosMistralByCodeCentroGestion(centroGestion);

            if (databaseInfo == null) {
                throw new NotFoundException(String.format(MessageText.DATABASE_MISTRAL_NOT_FOUND, centroGestion));
            }

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
        catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }
}
