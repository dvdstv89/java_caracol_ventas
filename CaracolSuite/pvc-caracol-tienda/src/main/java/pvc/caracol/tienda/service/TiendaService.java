package pvc.caracol.tienda.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.client.IMistralClient;

@Service
public class TiendaService extends BaseService implements ITiendaService {
    private final ModelMapper modelMapper;
    private final IMistralClient mistralClient;

    @Autowired
    public TiendaService(ModelMapper modelMapper, IMistralClient mistralClient) {
        this.modelMapper = modelMapper;
        this.mistralClient = mistralClient;
    }

    @Override
    public WebResponse findCajasActivasByCodeCentroGestion(String code) throws NotFoundException, FeignClientException {
        return mistralClient.getCajasActivasByCentroGestion(code);
    }
}
