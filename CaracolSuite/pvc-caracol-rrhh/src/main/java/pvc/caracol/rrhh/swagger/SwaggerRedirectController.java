package pvc.caracol.rrhh.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doc/rrhh")
public class SwaggerRedirectController {
    private final DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerRedirectController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public RedirectView swagger() {
        return new RedirectView("/swagger-ui/index.html");
    }

    @GetMapping("string")
    public String swaggerString() {
        List<ServiceInstance> instances = discoveryClient.getInstances(applicationName);
        if (!instances.isEmpty()) {
            ServiceInstance instance = instances.get(0);
            return instance.getUri() + "/swagger-ui/index.html";
        } else {
            return "No se encontraron instancias del microservicio empresarial";
        }
    }
}
