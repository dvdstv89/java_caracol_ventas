package pvc.caracol.documentation.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pvc.caracol.common.utils.SwaggerUtil;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {

        Info apiInfo = new Info()
                .title("Documentacion")
                .version("1.0")
                .description("Microservicio dedicado a centralizar las urls de los demás miscroservicios diponibles")
                .contact(SwaggerUtil.customContact());
        return new OpenAPI()
                .info(apiInfo);

    }
}
