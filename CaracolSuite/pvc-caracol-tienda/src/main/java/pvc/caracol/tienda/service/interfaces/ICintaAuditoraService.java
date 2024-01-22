package pvc.caracol.tienda.service.interfaces;


import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.tienda.http.input.ProcesarCintasTiendasDto;

public interface ICintaAuditoraService {
    ApiWebResponse procesarCientasAuditorasByTienda(ProcesarCintasTiendasDto procesarCintasTiendasDto) throws FeignClientException;
}
