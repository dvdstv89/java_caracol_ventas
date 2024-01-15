package pvc.caracol.tienda.swagger;

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
                .title("Tienda Services")
                .version("1.0 BETA")
                .description("Microservicion dedicado a manejar información sobre las tiendas.")
                .contact(SwaggerUtil.customContact());

        return new OpenAPI()
                .info(apiInfo);

    }
}
