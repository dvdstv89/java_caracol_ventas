package pvc.caracol.documentation.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@FeignClient(name = "pvc-caracol-planes")
public interface IPlanesClient {
    @GetMapping("/api/v1/doc/planes/string")
    public String swaggerString();
}
