package pvc.caracol.mistral.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {


        Info apiInfo = new Info()
                .title("Empresarial")
                .version("1.0 BETA")
                .description("La descripcion")
                .contact(new Contact()
                        .name("David Estévez Díaz")
                        .email("david.estevez@veste.caracol.cu"));

        return new OpenAPI()
                .info(apiInfo);

    }
}
