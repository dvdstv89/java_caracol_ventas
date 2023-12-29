package pvc.caracol.empresarial.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.common.reponse.WebResponse;
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
    public DataBaseMistralService(ICentroGestionRepository repository, ModelMapper modelMapper){
        this.repository=repository;
        this.modelMapper = modelMapper;
    }
    @Override
    public WebResponse findBaseDatosMistralByCodeCentroGestion(String code) throws NotFoundException {
        CentroGestion centroGestion = repository.findByCode(code);
        if(isNullOrEmpty(centroGestion)){
            throw new NotFoundException(MessageText.CENTRO_GESTION_NOT_FOUND);
        }
        DataBaseMistral dataBaseMistral = centroGestion.getDatabase();
        DataBaseMistralDTO dataBaseMistralDTO = modelMapper.map(dataBaseMistral, DataBaseMistralDTO.class);
        response.addOkResponse200(dataBaseMistralDTO);
        return response;
    }

    public WebResponse updateDatabaseConnexion(String code, DataBaseMistralDTO dataBaseMistralDTO) throws NotFoundException {
        CentroGestion centroGestion = repository.findByCode(code);
        if(isNullOrEmpty(centroGestion)){
            throw new NotFoundException(MessageText.CENTRO_GESTION_NOT_FOUND);
        }
        DataBaseMistral dataBaseMistral = modelMapper.map(dataBaseMistralDTO, DataBaseMistral.class);
        dataBaseMistral.setId(centroGestion.getDatabase().getId());
        dataBaseMistral.setCode(centroGestion.getDatabase().getCode());
        centroGestion.setDatabase(dataBaseMistral);
        repository.save(centroGestion);
        response.addOkResponse200(MessageText.CENTRO_GESTION_DATABASE_UPDATED);
        return response;
    }
}
