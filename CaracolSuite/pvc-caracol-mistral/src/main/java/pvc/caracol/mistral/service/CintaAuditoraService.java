package pvc.caracol.mistral.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundCausedException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.mistral.SystemMicroserviceMistralApplication;
import pvc.caracol.mistral.http.input.CajaRegistradoraDto;
import pvc.caracol.mistral.http.output.CintaAuditoraDto;
import pvc.caracol.mistral.messages.MessageText;
import pvc.caracol.mistral.model.CintaAuditora;
import pvc.caracol.mistral.repository.interfaces.ICintaAuditoraRepository;
import pvc.caracol.mistral.service.interfaces.ICintaAuditoraService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    private final ICintaAuditoraRepository cintaAuditoraRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ICintaAuditoraRepository repository, ModelMapper modelMapper) {
        this.cintaAuditoraRepository = repository;
        this.modelMapper = modelMapper;
    }

    public ApiWebResponse getCintaAuditora(CajaRegistradoraDto cajaRegistradora) throws NotFoundException, FeignClientException, NotFoundCausedException {

        if (cajaRegistradora.getFechaInicio().isBefore(SystemMicroserviceMistralApplication.fechaMinimaBuscarCintas)) {
            throw new NotFoundCausedException(HttpStatus.PRECONDITION_FAILED, MessageText.CINTA_AUDITORA_OBSOLETA);
        }

        List<CintaAuditora> cintaAuditoras = cintaAuditoraRepository.getCintaAuditora(cajaRegistradora);
        if (cintaAuditoras.isEmpty()) {
            throw new NotFoundException(MessageText.CINTA_AUDITORA_NOT_FOUND);
        }
        List<CintaAuditoraDto> cintaAuditoraDtos = cintaAuditoras.stream()
                .map(c -> modelMapper.map(c, CintaAuditoraDto.class))
                .collect(Collectors.toList());
        response.addOkResponse200(cintaAuditoraDtos);
        return response;
    }
}
