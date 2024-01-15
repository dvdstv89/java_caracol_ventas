package pvc.caracol.empresarial.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.empresarial.http.DataBaseMistralDTO;
import pvc.caracol.empresarial.messages.MessageText;
import pvc.caracol.empresarial.models.CentroGestion;
import pvc.caracol.empresarial.models.DataBaseMistral;
import pvc.caracol.empresarial.repositories.ICentroGestionRepository;

@Service
public class DataBaseMistralService extends BaseService implements IDataBaseMistralService {
    private final ICentroGestionRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public DataBaseMistralService(ICentroGestionRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiWebResponse findBaseDatosMistralByCodeCentroGestion(Integer idCentroGestion) throws NotFoundException {
        CentroGestion centroGestion = getCentroGestion(idCentroGestion);
        DataBaseMistral dataBaseMistral = centroGestion.getDatabase();
        DataBaseMistralDTO dataBaseMistralDTO = modelMapper.map(dataBaseMistral, DataBaseMistralDTO.class);
        response.addOkResponse200(dataBaseMistralDTO);
        return response;
    }

    @Override
    public ApiWebResponse updateDatabaseConnexion(Integer idCentroGestion, DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException {
        CentroGestion centroGestion = getCentroGestion(idCentroGestion);
        DataBaseMistral dataBaseMistral = modelMapper.map(dataBaseMistralDTO, DataBaseMistral.class);
        dataBaseMistral.setId(centroGestion.getDatabase().getId());
        dataBaseMistral.setCode(centroGestion.getDatabase().getCode());
        centroGestion.setDatabase(dataBaseMistral);
        repository.save(centroGestion);
        response.addOkResponse200(MessageText.CENTRO_GESTION_DATABASE_UPDATED);
        return response;
    }

    private CentroGestion getCentroGestion(Integer idCentroGestion) throws NotFoundException {
        return repository.findById(idCentroGestion)
                .orElseThrow(() -> new NotFoundException(String.format(MessageText.CENTRO_GESTION_NOT_FOUND, idCentroGestion)));
    }
}
