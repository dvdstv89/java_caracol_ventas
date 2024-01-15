package pvc.caracol.tienda.service.interfaces;


import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.tienda.http.output.TiendaDto;

public interface ICintaAuditoraService {

    ApiWebResponse getCintasAuditorasByTiendaProcesadas(TiendaDto tiendaDto) throws FeignClientException;
}
