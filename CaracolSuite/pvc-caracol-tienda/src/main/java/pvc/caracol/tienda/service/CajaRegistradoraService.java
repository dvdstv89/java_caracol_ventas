package pvc.caracol.tienda.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.client.IMistralClient;
import pvc.caracol.tienda.dtos.CajaDto;
import pvc.caracol.tienda.http.input.CintaAuditoraProcesadaDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.TiendaFisica;
import pvc.caracol.tienda.repository.ICajaRegistradoraRepository;
import pvc.caracol.tienda.service.interfaces.ICajaRegistradoraService;
import pvc.caracol.tienda.singleton.ISingletonList;

import java.util.List;

@Service
public class CajaRegistradoraService extends BaseService implements ICajaRegistradoraService {
    private final ModelMapper modelMapper;
    private final IMistralClient mistralClient;

    private ISingletonList<CajaRegistradora> cajaRegistradoraISingleton;

    private final ICajaRegistradoraRepository cajaRegistradoraRepository;

    @Autowired
    public CajaRegistradoraService(ModelMapper modelMapper, ICajaRegistradoraRepository cajaRegistradoraRepository, IMistralClient mistralClient, ISingletonList<CajaRegistradora> cajaRegistradoraISingleton) {
        this.modelMapper = modelMapper;
        this.mistralClient = mistralClient;
        this.cajaRegistradoraRepository = cajaRegistradoraRepository;
        this.cajaRegistradoraISingleton = cajaRegistradoraISingleton;
    }

    @Override
    public List<CajaDto> getCintasAuditorasByIdTienda(String id) throws FeignClientException {
        try {
            ApiWebResponse apiWebResponseCajas = mistralClient.getCajasActivasByTienda(id);
            List<CajaDto> list = apiWebResponseCajas.getResultAsList().stream()
                    .map(objeto -> modelMapper.map(objeto, CajaDto.class))
                    .toList();
            return list;
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }

    @Override
    public CajaRegistradora buscarCajaRegistradora(TiendaFisica tienda, CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        CajaRegistradora cajaRegistradora = cajaRegistradoraISingleton.findFirst(
                c -> c.getIdCaja().equals(cintaAuditoraProcesadas.getIdCaja())
                        && c.getCodigoAlmacen().equals(cintaAuditoraProcesadas.getCodigoAlmacen())
                        && c.getCodigoRed().equals(cintaAuditoraProcesadas.getCodigoRed())
        );

        if (cajaRegistradora == null) {
            cajaRegistradora = cajaRegistradoraRepository.findByIdCajaAndCodigoAlmacenAndCodigoRedAndTienda(
                            cintaAuditoraProcesadas.getIdCaja(),
                            cintaAuditoraProcesadas.getCodigoAlmacen(),
                            cintaAuditoraProcesadas.getCodigoRed(),
                            tienda)
                    .orElseGet(() -> createAndSaveNewCajaRegistradora(tienda, cintaAuditoraProcesadas));
            cajaRegistradoraISingleton.add(cajaRegistradora);
        }
        return cajaRegistradora;
    }

    private CajaRegistradora createAndSaveNewCajaRegistradora(TiendaFisica tienda, CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        CajaRegistradora cajaRegistradora = new CajaRegistradora();
        cajaRegistradora.setIdCaja(cintaAuditoraProcesadas.getIdCaja());
        cajaRegistradora.setCodigoAlmacen(cintaAuditoraProcesadas.getCodigoAlmacen());
        cajaRegistradora.setCodigoRed(cintaAuditoraProcesadas.getCodigoRed());
        cajaRegistradora.setTienda(tienda);
        return cajaRegistradoraRepository.save(cajaRegistradora);
    }
}
