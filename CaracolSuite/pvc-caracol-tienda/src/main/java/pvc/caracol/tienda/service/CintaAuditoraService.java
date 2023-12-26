package pvc.caracol.tienda.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.tienda.client.ICinadlClient;
import pvc.caracol.tienda.client.IMistralClient;
import pvc.caracol.tienda.http.input.CintaAuditoraDto;
import pvc.caracol.tienda.http.output.CintaAuditoraResponse;

import java.io.IOException;
import java.util.List;

@Service
public class CintaAuditoraService implements ICintaAuditoraService {
    private final IMistralClient mistralClient;
    private final ICinadlClient cinadlClient;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ModelMapper modelMapper, ICinadlClient cinadlClient, IMistralClient mistralClient) {
        this.modelMapper = modelMapper;
        this.cinadlClient = cinadlClient;
        this.mistralClient = mistralClient;
    }

    @Override
    public ApiResponse getCintasAuditoras(CintaAuditoraResponse cintaAuditoraRequest) throws IOException, FeignClientException {
        try {
            List<CintaAuditoraDto> cintasAuditoras = mistralClient.getCintasAuditoras(cintaAuditoraRequest);
            return procesarCintasAuditoras(cintasAuditoras);
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }

    @Override
    public ApiResponse procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws IOException {
        //todo aumentar las cintas auditoras a revisar
        byte[] bytes = cintaAuditoraDtos.get(0).getFichero();
        return cinadlClient.analizarCintaAuditora(bytes);
    }
}