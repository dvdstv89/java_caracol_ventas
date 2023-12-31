package pvc.caracol.mistral.swagger;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.utils.SwaggerUtil;

@RestController
//@Hidden
@Tag(name = "Swagger", description = "Tutorial management APIs")
@RequestMapping("/api/v1/doc/mistral")
public class SwaggerRedirectController {
    private final DiscoveryClient discoveryClient;
    @Value("${spring.application.name}")
    private String applicationName;

    public SwaggerRedirectController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Operation(
           // operationId = "2",
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status."
//           ,tags = { "tutorials", "get" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = RedirectView.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public RedirectView swagger() {
        return SwaggerUtil.swagger();
    }

    @Operation(
          //  operationId = "1",
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status."
            //,hidden = true
//            ,tags = { "tutorials", "get" }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("string")
    public String swaggerString() throws NotFoundException {
        return SwaggerUtil.getSwaggerUrl(discoveryClient, applicationName);
    }
}
