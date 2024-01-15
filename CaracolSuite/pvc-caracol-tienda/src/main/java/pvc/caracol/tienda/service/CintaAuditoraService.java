package pvc.caracol.tienda.service;

import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pvc.caracol.common.exceptions.FeignClientException;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.common.utils.DateUtil;
import pvc.caracol.tienda.client.ICinadClient;
import pvc.caracol.tienda.client.IMistralClient;
import pvc.caracol.tienda.dtos.CajaDto;
import pvc.caracol.tienda.http.CintaAuditoraDto;
import pvc.caracol.tienda.http.input.CintaAuditoraProcesadaDto;
import pvc.caracol.tienda.http.input.DiaOperacionDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;
import pvc.caracol.tienda.http.output.TiendaDto;
import pvc.caracol.tienda.mapper.CajaMapper;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.CintaAuditora;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.TiendaFisica;
import pvc.caracol.tienda.repository.ICintaAuditoraRepository;
import pvc.caracol.tienda.service.interfaces.*;
import pvc.caracol.tienda.singleton.ISingletonList;

import java.util.ArrayList;
import java.util.List;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    private final IMistralClient mistralClient;
    private final ICinadClient cinadClient;
    private final ModelMapper modelMapper;
    private final ITiendaFisicaService tiendaService;
    private final ICajaRegistradoraService cajaRegistradoraService;
    private final IDiaOperacionService diaOperacionService;
    private final ICintaAuditoraRepository cintaAuditoraRepository;

    private final IProductoDiaOPeracionCajaService productoDiaOPeracionCajaService;
    private final ITransaccionPagoService transaccionPagoService;

    private final ICajeroService cajeroService;
    private ISingletonList<CintaAuditora> cintaAuditoraISingleton;

    @Autowired
    public CintaAuditoraService(ICinadClient cinadClient, ITiendaFisicaService tiendaService, ICintaAuditoraRepository cintaAuditoraRepository,
                                IDiaOperacionService diaOperacionService, IMistralClient mistralClient, ICajaRegistradoraService cajaRegistradoraService,
                                ISingletonList<CintaAuditora> cintaAuditoraISingleton, IProductoDiaOPeracionCajaService productoDiaOPeracionCajaService,
                                ITransaccionPagoService transaccionPagoService, ICajeroService cajeroService, ModelMapper modelMapper) {
        this.cinadClient = cinadClient;
        this.mistralClient = mistralClient;
        this.modelMapper = modelMapper;
        this.cajaRegistradoraService = cajaRegistradoraService;
        this.tiendaService = tiendaService;
        this.cintaAuditoraRepository = cintaAuditoraRepository;
        this.diaOperacionService = diaOperacionService;
        this.productoDiaOPeracionCajaService = productoDiaOPeracionCajaService;
        this.cintaAuditoraISingleton = cintaAuditoraISingleton;
        this.transaccionPagoService = transaccionPagoService;
        this.cajeroService = cajeroService;
    }

    @Override
    public ApiWebResponse getCintasAuditorasByTiendaProcesadas(TiendaDto tiendaDto) throws FeignClientException {
        List<CajaDto> cajaDtos = cajaRegistradoraService.getCintasAuditorasByIdTienda(tiendaDto.getCodigoTienda());

        List<CintaAuditoraProcesadaDto> processedCintas = new ArrayList<>();
        CajaMapper cajaMapper = new CajaMapper(tiendaDto);

        for (CajaDto caja : cajaDtos) {
            CajaRegistradoraDto cajaRegistradoraDto = cajaMapper.map(caja);
            List<CintaAuditoraDto> cintaAuditoraDtoList = getCintasAuditorasByCaja(cajaRegistradoraDto);
            if (cintaAuditoraDtoList != null && !cintaAuditoraDtoList.isEmpty()) {
                List<CintaAuditoraProcesadaDto> cintaAuditoraProcesadaDtos = procesarCintasAuditoras(cintaAuditoraDtoList);
                processedCintas.addAll(cintaAuditoraProcesadaDtos);
            }
        }
        response.addOkResponse200(processedCintas);
        return response;
    }

    private List<CintaAuditoraDto> getCintasAuditorasByCaja(CajaRegistradoraDto cajaRegistradoraDto) throws FeignClientException {
        try {
            ApiWebResponse apiWebResponse = mistralClient.getCintasAuditoras(cajaRegistradoraDto);
            List<CintaAuditoraDto> cintaAuditoraDtos = apiWebResponse.getResultAsList().stream()
                    .map(objeto -> modelMapper.map(objeto, CintaAuditoraDto.class))
                    .toList();
            cintaAuditoraDtos.forEach(cintaAuditoraDto -> cintaAuditoraDto.setIdTienda(cajaRegistradoraDto.getIdTienda()));
            return cintaAuditoraDtos;
        } catch (FeignException feignException) {
            if (HttpStatus.resolve(feignException.status()).is4xxClientError()) {
                return null;
            }
            throw new FeignClientException(feignException);
        }
    }

    private List<CintaAuditoraProcesadaDto> procesarCintasAuditoras(List<CintaAuditoraDto> cintaAuditoraDtos) throws FeignClientException {
        List<CintaAuditoraProcesadaDto> processedCintas = new ArrayList<>();
        for (CintaAuditoraDto cintaAuditoraDto : cintaAuditoraDtos) {
            CintaAuditoraProcesadaDto processedCinta = cintaFueAnalizada(cintaAuditoraDto)
                    ? getAnalisisCinta(cintaAuditoraDto)
                    : procesarCinta(cintaAuditoraDto);
            processedCintas.add(processedCinta);
        }
        return processedCintas;
    }

    private CintaAuditoraProcesadaDto procesarCinta(CintaAuditoraDto cintaAuditoraDto) throws FeignClientException {
        try {
            CintaAuditoraProcesadaDto cintaAuditoraProcesadaDto = cinadClient.analizarCintaAuditora(cintaAuditoraDto.getFichero());
            cintaAuditoraProcesadaDto.setIdCaja(cintaAuditoraDto.getIdCaja());
            cintaAuditoraProcesadaDto.setCodigoAlmacen(cintaAuditoraDto.getCodigoAlmacen());
            cintaAuditoraProcesadaDto.setCodigoRed(cintaAuditoraDto.getCodigoRed());
            cintaAuditoraProcesadaDto.setIdTienda(cintaAuditoraDto.getIdTienda());
            guardarAnalisis(cintaAuditoraProcesadaDto);
            return cintaAuditoraProcesadaDto;
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }

    private void guardarAnalisis(CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        TiendaFisica tienda = tiendaService.buscarTiendaFisica(cintaAuditoraProcesadas.getIdTienda());
        CajaRegistradora cajaRegistradora = cajaRegistradoraService.buscarCajaRegistradora(tienda, cintaAuditoraProcesadas);
        CintaAuditora cintaAuditora = buscarCintaAuditora(cajaRegistradora, cintaAuditoraProcesadas);
        for (DiaOperacionDto diaOperacionDto : cintaAuditoraProcesadas.getDiaOperacions()) {
            DiaOperacion diaOperacion = diaOperacionService.buscarDiaOperacion(diaOperacionDto, cajaRegistradora, cintaAuditora);
            if (diaOperacion == null) {
                //todo si dia de operacion es null significa que el dia ya existe
                //todo puede ser falso
            } else {
                //todo guardar cinta
                productoDiaOPeracionCajaService.procesarProductosDiaOperacion(diaOperacionDto.getProductos(), diaOperacion);
                transaccionPagoService.procesarTransaccionPago(diaOperacionDto.getFormasPago(), diaOperacion);
                transaccionPagoService.procesarPropinas(diaOperacionDto.getPropinas(), diaOperacion);
                cajeroService.procesarCajerosDiaOperacion(diaOperacionDto.getCajeros(), diaOperacion, cajaRegistradora);
            }
        }
        return;
    }

    private boolean cintaFueAnalizada(CintaAuditoraDto cintaAuditoraDto) {
        return false;
        //todo buscar cinta
    }

    private CintaAuditoraProcesadaDto getAnalisisCinta(CintaAuditoraDto cintaAuditoraDto) {
        return new CintaAuditoraProcesadaDto();
        //todo buscar analisis de cinta
    }

    private CintaAuditora buscarCintaAuditora(CajaRegistradora cajaRegistradora, CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        CintaAuditora cintaAuditora = cintaAuditoraISingleton.findFirst(
                c -> c.getFechaCreacion().equals(DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaCreacion()))
                        && c.getFechaHaladoVenta().equals(DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaHaladoVenta()))
                        && c.getCajaRegistradora().getId().equals(cajaRegistradora.getId())
        );

        if (cintaAuditora == null) {
            cintaAuditora = cintaAuditoraRepository.findByFechaCreacionAndFechaHaladoVentaAndCajaRegistradora(
                            DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaCreacion()),
                            DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaHaladoVenta()),
                            cajaRegistradora)
                    .orElseGet(() -> createAndSaveNewCintaAuditora(cajaRegistradora, cintaAuditoraProcesadas));
            cintaAuditoraISingleton.add(cintaAuditora);
        }
        return cintaAuditora;
    }

    private CintaAuditora createAndSaveNewCintaAuditora(CajaRegistradora cajaRegistradora, CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        CintaAuditora cintaAuditora = new CintaAuditora();
        cintaAuditora.setByteSize(cintaAuditoraProcesadas.getByteSize());
        cintaAuditora.setCountLines(cintaAuditoraProcesadas.getCountLines());
        cintaAuditora.setFechaAnalisis(DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaAnalisis()));
        cintaAuditora.setFechaCreacion(DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaCreacion()));
        cintaAuditora.setFechaHaladoVenta(DateUtil.convertirToDateTime(cintaAuditoraProcesadas.getFechaHaladoVenta()));
        cintaAuditora.setPaperSize(cintaAuditoraProcesadas.getPaperSizeCentimetros());
        cintaAuditora.setCajaRegistradora(cajaRegistradora);
        return cintaAuditoraRepository.save(cintaAuditora);
    }
}