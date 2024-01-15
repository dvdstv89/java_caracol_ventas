package pvc.caracol.mistral.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.mistral.client.IEmpresarialClient;
import pvc.caracol.mistral.dtos.CajaDto;
import pvc.caracol.mistral.http.input.TiendaDto;
import pvc.caracol.mistral.messages.MessageText;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;
import pvc.caracol.mistral.service.interfaces.ICajaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajaService extends BaseService implements ICajaService {

    private final ICajaRepository repository;
    private final ModelMapper modelMapper;

    private final IEmpresarialClient empresarialClient;

    @Autowired
    public CajaService(ICajaRepository repository, ModelMapper modelMapper, IEmpresarialClient empresarialClient) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.empresarialClient = empresarialClient;
    }

    @Override
    public ApiWebResponse getCajasActivasByCentroGestion(Integer idCentroGestion) throws NotFoundException, FeignClientException {
        List<Caja> cajas = repository.getCajasActivasByCentroGestion(idCentroGestion);
        if (cajas.isEmpty()) {
            throw new NotFoundException(MessageText.CAJA_REGISTRADORAS_NOT_FOUND);
        }
        List<CajaDto> cajaDtos = cajas.stream()
                .map(c -> modelMapper.map(c, CajaDto.class))
                .collect(Collectors.toList());
        response.addOkResponse200(cajaDtos);
        return response;
    }

    @Override
    public ApiWebResponse getCajasActivasByTienda(String codigoTienda) throws FeignClientException, NotFoundException {
        try {
            ApiWebResponse apiWebResponse = empresarialClient.findTiendaByIdTienda(codigoTienda);
            TiendaDto tiendaDto = modelMapper.map(apiWebResponse.getResultAsMap(), TiendaDto.class);
            List<Caja> cajas = repository.getCajasActivasByTienda(tiendaDto);
            if (cajas.isEmpty()) {
                throw new NotFoundException(MessageText.CAJA_REGISTRADORAS_NOT_FOUND);
            }
            List<CajaDto> cajaDtos = cajas.stream()
                    .map(c -> modelMapper.map(c, CajaDto.class))
                    .toList();
            response.addOkResponse200(cajaDtos);
            return response;

        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }
}
