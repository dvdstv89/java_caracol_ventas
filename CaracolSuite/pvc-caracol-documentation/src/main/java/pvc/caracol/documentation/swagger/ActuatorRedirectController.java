package pvc.caracol.documentation.swagger;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pvc.caracol.common.utils.SwaggerUtil;

@RestController
@Hidden
@RequestMapping("/actuator/info")
public class ActuatorRedirectController {
    @GetMapping
    public RedirectView swagger() {
        return SwaggerUtil.swagger();
    }
}
