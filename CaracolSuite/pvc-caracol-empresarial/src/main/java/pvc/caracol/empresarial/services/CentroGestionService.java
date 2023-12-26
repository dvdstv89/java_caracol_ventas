package pvc.caracol.empresarial.services;

import pvc.caracol.empresarial.http.output.DataBaseMistralDTO;
import pvc.caracol.empresarial.models.CentroGestion;
import pvc.caracol.empresarial.models.DataBaseMistral;
import pvc.caracol.empresarial.repositories.ICentroGestionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentroGestionService implements ICentroGestionService{
    private final ICentroGestionRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public CentroGestionService(ICentroGestionRepository repository, ModelMapper modelMapper){
        this.repository=repository;
        this.modelMapper = modelMapper;
    }
    @Override
    public DataBaseMistralDTO findBaseDatosMistralByCodeCentroGestion(String code) {
        CentroGestion centroGestion = repository.findByCode(code);
        if (centroGestion != null){
            DataBaseMistral dataBaseMistral = centroGestion.getDatabase();
            DataBaseMistralDTO dataBaseMistralDTO = modelMapper.map(dataBaseMistral, DataBaseMistralDTO.class);
            return  dataBaseMistralDTO;
        }
        return null; // todo control de excepciones
    }
}
