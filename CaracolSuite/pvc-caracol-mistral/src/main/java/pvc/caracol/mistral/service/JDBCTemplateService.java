package pvc.caracol.mistral.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
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
    private final ModelMapper modelMapper;

    @Autowired
    public JDBCTemplateService(JdbcTemplateSingleton databaseInfoSingleton, IEmpresarialClient empresarialClient, ModelMapper modelMapper) {
        this.databaseInfoSingleton = databaseInfoSingleton;
        this.empresarialClient = empresarialClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public JdbcTemplate getJdbcTemplate(Integer idCentroGestion) throws NotFoundException, FeignClientException {
        JdbcTemplate jdbcTemplate = databaseInfoSingleton.getJdbcTemplate(idCentroGestion);
        if (jdbcTemplate == null) {
            jdbcTemplate = createAndCacheJdbcTemplate(idCentroGestion);
        }
        return jdbcTemplate;
    }

    private JdbcTemplate createAndCacheJdbcTemplate(Integer idCentroGestion) throws NotFoundException, FeignClientException {
        try {
            ApiWebResponse apiWebResponse = empresarialClient.findBaseDatosMistralByCodeCentroGestion(idCentroGestion);

            if (apiWebResponse == null) {
                throw new NotFoundException(String.format(MessageText.DATABASE_MISTRAL_NOT_FOUND, idCentroGestion));
            }

            DataBaseInfo databaseInfo = modelMapper.map(apiWebResponse.getResult(), DataBaseInfo.class);
            String databaseUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;trustServerCertificate=true",
                    databaseInfo.getHost(), databaseInfo.getPort(), databaseInfo.getName());

            DataSource dataSource = DataSourceBuilder.create()
                    .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                    .url(databaseUrl)
                    .username(databaseInfo.getUsername())
                    .password(databaseInfo.getPassword())
                    .build();

            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            databaseInfoSingleton.addJdbcTemplate(idCentroGestion, jdbcTemplate);
            return jdbcTemplate;
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }
}
