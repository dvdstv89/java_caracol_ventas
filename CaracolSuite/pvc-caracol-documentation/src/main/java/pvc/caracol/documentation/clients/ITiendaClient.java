package pvc.caracol.documentation.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@FeignClient(name = "pvc-caracol-tienda")
public interface ITiendaClient {
    @GetMapping("/api/v1/doc/tienda")
    public RedirectView swagger();
}
