package pvc.caracol.mistral.swagger;

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
                .title("Mistral Services")
                .version("0.1")
                .description("Microservicio dedicado a interactuar con las bases de datos del sistema de control de inventario MISTRAL CARIBE")
                .contact(SwaggerUtil.customContact());
        return new OpenAPI()
                .info(apiInfo);
    }
}
