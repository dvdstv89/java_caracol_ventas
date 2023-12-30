package pvc.caracol.documentation.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@FeignClient(name = "pvc-caracol-empresarial")
public interface IEmpresarialClient {
    @GetMapping("/api/v1/doc/empresarial")
    public RedirectView swagger();
}
