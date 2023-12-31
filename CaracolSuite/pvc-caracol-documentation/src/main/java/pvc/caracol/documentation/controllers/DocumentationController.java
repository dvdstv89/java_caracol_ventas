package pvc.caracol.documentation.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pvc.caracol.documentation.clients.*;

@RestController
@RequestMapping("/v1/doc")
public class DocumentationController {

    private final IEmpresarialClient empresarialClient;
    private final ITiendaClient tiendaClient;
    private final IMistralClient mistralClient;
    private final IEnergeticoClient energeticoClient;
    private final IPlanesClient planesClient;
    private final IPartesClient partesClient;
    private final IReportesClient reportesClient;
    private final IRrhhClient rrhhClient;
    private final IZunClient zunClient;
    private final ICinadClient cinadClient;

    @Autowired
    public DocumentationController(IEmpresarialClient empresarialClient, ITiendaClient tiendaClient, IMistralClient mistralClient,
                                   IEnergeticoClient energeticoClient, IPlanesClient planesClient, IPartesClient partesClient,
                                   IReportesClient reportesClient, IRrhhClient rrhhClient, IZunClient zunClient, ICinadClient cinadClient) {
        this.empresarialClient = empresarialClient;
        this.tiendaClient = tiendaClient;
        this.mistralClient = mistralClient;
        this.energeticoClient = energeticoClient;
        this.planesClient = planesClient;
        this.partesClient = partesClient;
        this.reportesClient = reportesClient;
        this.rrhhClient = rrhhClient;
        this.zunClient = zunClient;
        this.cinadClient = cinadClient;
    }

    @GetMapping("swagger")
    public RedirectView getSwaggerDocumentationMicroservice() {
        return new RedirectView("/swagger-ui/index.html");
    }

    @Operation(
            //  operationId = "1",
            summary = "Swagger Empresarial Documentation",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status."
            //,hidden = true
//            ,tags = { "tutorials", "get" }
    )
    @GetMapping("swagger/empresarial")
    public RedirectView getSwaggerEmpresarialMicroservice() {
        String swaggerUrl = empresarialClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/tienda")
    public RedirectView getSwaggerTiendaMicroservice() {
        String swaggerUrl = tiendaClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/mistral")
    public RedirectView getSwaggerMistralMicroservice() {
        String swaggerUrl = mistralClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/energetico")
    public RedirectView getSwaggerEnergeticoMicroservice() {
        String swaggerUrl = energeticoClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/planes")
    public RedirectView getSwaggerPlanesMicroservice() {
        String swaggerUrl = planesClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/partes")
    public RedirectView getSwaggerPartesMicroservice() {
        String swaggerUrl = partesClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/reportes")
    public RedirectView getSwaggerReportesMicroservice() {
        String swaggerUrl = reportesClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/rrhh")
    public RedirectView getSwaggerRrhhMicroservice() {
        String swaggerUrl = rrhhClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/zun")
    public RedirectView getSwaggerZunMicroservice() {
        String swaggerUrl = zunClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    @GetMapping("swagger/cinad")
    public RedirectView getSwaggerCinadMicroservice() {
        String swaggerUrl = cinadClient.swaggerString();
        return redirectToUrl(swaggerUrl);
    }

    private RedirectView redirectToUrl(String swaggerUrl) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(swaggerUrl);
        return redirectView;
    }
}
