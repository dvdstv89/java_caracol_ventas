package pvc.caracol.empresarial.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.empresarial.http.output.TiendaDto;
import pvc.caracol.empresarial.messages.MessageText;
import pvc.caracol.empresarial.models.Tienda;
import pvc.caracol.empresarial.repositories.ITiendaRepository;

@Service
public class TiendaService extends BaseService implements ITiendaService {
    private final ITiendaRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public TiendaService(ITiendaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiWebResponse findTiendaById(String codeTienda) throws NotFoundException {
        Tienda tienda = getTienda(codeTienda);
        TiendaDto tiendaDto = TiendaDto.builder()
                .codeTienda(tienda.getCode())
                .idCentroGestion(tienda.getCentroGestion().getId())
                .build();
        response.addOkResponse200(tiendaDto);
        return response;
    }

    private Tienda getTienda(String codeTienda) throws NotFoundException {
        return repository.findById(codeTienda)
                .orElseThrow(() -> new NotFoundException(String.format(MessageText.TIENDA_NOT_FOUND, codeTienda)));
    }
}
