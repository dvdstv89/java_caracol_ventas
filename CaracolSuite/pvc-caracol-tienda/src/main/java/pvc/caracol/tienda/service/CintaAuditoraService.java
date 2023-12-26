package pvc.caracol.tienda.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.client.ICinadlClient;
import pvc.caracol.tienda.client.IMistralClient;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.input.CintaAuditoraProcesadaDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    //todo usar servicio base
    private final IMistralClient mistralClient;
    private final ICinadlClient cinadlClient;

    @Autowired
    public CintaAuditoraService(ICinadlClient cinadlClient, IMistralClient mistralClient) {
        this.cinadlClient = cinadlClient;
        this.mistralClient = mistralClient;
    }

    @Override
    public ApiResponse getCintasAuditorasByCaja(CajaRegistradoraDto cajaRegistradoraDto) throws IOException, FeignClientException {
        try {
            List<CintaAuditoraDto> cintasAuditoras = mistralClient.getCintasAuditoras(cajaRegistradoraDto);
            return procesarCintasAuditoras(cintasAuditoras);
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }

    @Override
    public ApiResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException {
        List<CintaAuditoraProcesadaDto> cintaAuditoraElectronicas = cintaAuditoraDtos.stream()
                .map(cintaAuditoraDto -> {
                    try {
                        return procesarCinta(cintaAuditoraDto);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        response.addOkResponse200(cintaAuditoraElectronicas);
        return response;
    }

    private CintaAuditoraProcesadaDto procesarCinta(CintaAuditoraDto cintaAuditoraDto) throws IOException {
        return cinadlClient.analizarCintaAuditora(cintaAuditoraDto);
    }
}