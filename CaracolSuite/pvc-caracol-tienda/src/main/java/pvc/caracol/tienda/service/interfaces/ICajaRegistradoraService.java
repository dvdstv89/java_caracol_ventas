package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.tienda.dtos.CajaDto;
import pvc.caracol.tienda.http.input.CintaAuditoraProcesadaDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.TiendaFisica;

import java.util.List;

public interface ICajaRegistradoraService {
    List<CajaDto> getCintasAuditorasByIdTienda(String id) throws FeignClientException;
    CajaRegistradora buscarCajaRegistradora(TiendaFisica tienda, CintaAuditoraProcesadaDto cintaAuditoraProcesadas);
}
