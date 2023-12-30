package pvc.caracol.energetico.swagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/doc/energetico")
public class SwaggerRedirectController {
    @GetMapping
    public RedirectView swagger() {
        return new RedirectView("/swagger-ui/index.html");
    }
}