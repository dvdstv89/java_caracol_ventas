package pvc.caracol.mistral.repository.interfaces;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.exceptions.NotFoundException;
import pvc.caracol.mistral.http.input.TiendaDto;
import pvc.caracol.mistral.model.Caja;

import java.util.List;

public interface ICajaRepository {
    List<Caja> getCajasActivasByCentroGestion(Integer idCentroGestion) throws NotFoundException, FeignClientException;

    List<Caja> getCajasActivasByTienda(TiendaDto tiendaDto) throws FeignClientException, NotFoundException;
}

