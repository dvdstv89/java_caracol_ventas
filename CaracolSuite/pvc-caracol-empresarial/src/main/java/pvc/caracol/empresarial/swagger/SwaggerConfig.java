package pvc.caracol.empresarial.swagger;

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
                .title("Empresarial Services")
                .version("1.0 BETA")
                .description("Micorservicio dedicado para gestionar la estructura de la empresa")
                .contact(SwaggerUtil.customContact());
        return new OpenAPI()
                .info(apiInfo);

    }
}
