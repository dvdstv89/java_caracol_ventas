package pvc.caracol.common.utils;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.servlet.view.RedirectView;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.messages.MessageText;

import java.util.List;

public class SwaggerUtil {

    private static final String URL_SWAGGER = "/swagger-ui/index.html";
    public static final String URL_API_DOCS = "/v3/api-docs";

    public static Contact customContact() {
        return new Contact()
                .name("David Estévez Díaz [david.estevez@veste.caracol.cu]")
                .email("david.estevez@veste.caracol.cu");
    }

    public static RedirectView swagger() {
        return new RedirectView(SwaggerUtil.URL_SWAGGER);
    }

    public static String getSwaggerUrl(DiscoveryClient discoveryClient, String applicationName) throws NotFoundException {
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        if (!instances.isEmpty()) {
            ServiceInstance instance = instances.get(0);
            return instance.getUri() + SwaggerUtil.URL_SWAGGER;
        }
        throw new NotFoundException(String.format(MessageText.SWAGGER_NOT_FOUND, applicationName));
    }
}
