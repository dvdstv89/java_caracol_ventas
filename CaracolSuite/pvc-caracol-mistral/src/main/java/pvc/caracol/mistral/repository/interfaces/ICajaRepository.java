package pvc.caracol.mistral.repository.interfaces;

import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.model.Caja;

import java.util.List;

public interface ICajaRepository {
    List<Caja> getCajasActivas(String centroGestion) throws NotFoundException;
}
