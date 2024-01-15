package pvc.caracol.energetico.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pvc.caracol.common.utils.SwaggerUtil;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {


        Info apiInfo = new Info()
                .title("Energético Services")
                .version("1.0 BETA")
                .description("Microservicio dedicado a gestionar los metrocontadores de electricidad y agua de la empresa")
                .contact(SwaggerUtil.customContact());
        return new OpenAPI()
                .info(apiInfo);

    }
}
