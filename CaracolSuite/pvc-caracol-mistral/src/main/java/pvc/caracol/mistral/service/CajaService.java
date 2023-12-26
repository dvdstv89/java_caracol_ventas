package pvc.caracol.mistral.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.messages.MessageText;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.mistral.dtos.CajaDto;
import pvc.caracol.mistral.model.Caja;
import pvc.caracol.mistral.repository.interfaces.ICajaRepository;
import pvc.caracol.mistral.service.interfaces.ICajaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajaService extends BaseService implements ICajaService {

    private final ICajaRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public CajaService(ICajaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Caja> getCajasActivas() throws NotFoundException {
        return repository.getCajasActivas("23205");
    }

    @Override
    public ApiResponse getCajasActivasByCentroGestion(String centroGestion) throws NotFoundException {
        List<Caja> cajas = repository.getCajasActivas(centroGestion);
        if (cajas.isEmpty()) {
            throw new NotFoundException(MessageText.DRONE_NOT_FOUND_AVAILABLE_FOR_LOADING);
        }
        List<CajaDto> cajaDtos = cajas.stream()
                .map(c -> modelMapper.map(c, CajaDto.class))
                .collect(Collectors.toList());
        response.addOkResponse200(cajaDtos);
        return response;
    }
}
