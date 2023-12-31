package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.client.ICinadClient;
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
    private final ICinadClient cinadClient;

    @Autowired
    public CintaAuditoraService(ICinadClient cinadClient, IMistralClient mistralClient) {
        this.cinadClient = cinadClient;
        this.mistralClient = mistralClient;
    }

    @Override
    public WebResponse getCintasAuditorasByCaja(CajaRegistradoraDto cajaRegistradoraDto) throws IOException, NotFoundException, FeignClientException{
        List<CintaAuditoraDto> cintasAuditoras = mistralClient.getCintasAuditoras(cajaRegistradoraDto);
        return procesarCintasAuditoras(cintasAuditoras);
    }

    @Override
    public WebResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException {
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
        return cinadClient.analizarCintaAuditora(cintaAuditoraDto);
    }
}