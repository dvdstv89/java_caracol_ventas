package pvc.caracol.documentation.controllers;

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

    @GetMapping("swagger/empresarial")
    public RedirectView getSwaggerEmpresarialMicroservice() {
        return empresarialClient.swagger();
    }

    @GetMapping("swagger/tienda")
    public RedirectView getSwaggerTiendaMicroservice() {
        return tiendaClient.swagger();
    }

    @GetMapping("swagger/mistral")
    public RedirectView getSwaggerMistralMicroservice() {
        return mistralClient.swagger();
    }

    @GetMapping("swagger/energetico")
    public RedirectView getSwaggerEnergeticoMicroservice() {
        return energeticoClient.swagger();
    }

    @GetMapping("swagger/planes")
    public RedirectView getSwaggerPlanesMicroservice() {
        return planesClient.swagger();
    }

    @GetMapping("swagger/partes")
    public RedirectView getSwaggerPartesMicroservice() {
        return partesClient.swagger();
    }

    @GetMapping("swagger/reportes")
    public RedirectView getSwaggerReportesMicroservice() {
        return reportesClient.swagger();
    }

    @GetMapping("swagger/rrhh")
    public RedirectView getSwaggerRrhhMicroservice() {
        return rrhhClient.swagger();
    }

    @GetMapping("swagger/zun")
    public RedirectView getSwaggerZunMicroservice() {
        return zunClient.swagger();
    }

    @GetMapping("swagger/cinad")
    public RedirectView getSwaggerCinadMicroservice() {
        return cinadClient.swagger();
    }
}
