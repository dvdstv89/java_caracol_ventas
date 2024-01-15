package pvc.caracol.empresarial.swagger;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.utils.SwaggerUtil;

@RestController
@Hidden
@RequestMapping("/api/v1/doc/empresarial")
public class SwaggerRedirectController {
    private final DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerRedirectController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping
    public RedirectView swagger() {
        return SwaggerUtil.swagger();
    }

    @GetMapping("string")
    public String swaggerString() throws NotFoundException {
        return SwaggerUtil.getSwaggerUrl(discoveryClient, applicationName);
    }
}
