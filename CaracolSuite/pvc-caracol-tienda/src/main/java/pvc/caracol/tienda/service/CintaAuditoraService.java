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
import pvc.caracol.tienda.http.input.ProcesarCintasTiendasDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;
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
    public ApiWebResponse procesarCientasAuditorasByTienda(ProcesarCintasTiendasDto procesarCintasTiendasDto) throws FeignClientException {
        List<CajaDto> cajaDtos = cajaRegistradoraService.getCintasAuditorasByIdTienda(procesarCintasTiendasDto.getCodigoTienda());

        List<CintaAuditoraProcesadaDto> processedCintas = new ArrayList<>();
        CajaMapper cajaMapper = new CajaMapper(procesarCintasTiendasDto);

        for (CajaDto caja : cajaDtos) {
            CajaRegistradoraDto cajaRegistradoraDto = cajaMapper.map(caja);
            List<CintaAuditoraDto> cintaAuditoraDtoList = getCintasAuditorasByCaja(cajaRegistradoraDto);
            if (cintaAuditoraDtoList != null && !cintaAuditoraDtoList.isEmpty()) {
                processedCintas.addAll(procesarCintasAuditoras(cintaAuditoraDtoList));
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
            if (!cintaFueAnalizada(cintaAuditoraDto)) {
                CintaAuditoraProcesadaDto cintaAuditoraProcesadaDto =procesarCinta(cintaAuditoraDto);
                if(cintaAuditoraProcesadaDto!= null) processedCintas.add(procesarCinta(cintaAuditoraDto));
            }
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
            cintaAuditoraProcesadaDto.setFechaCreacionMistal(cintaAuditoraDto.getFechaCreacion());
            cintaAuditoraProcesadaDto.setFechaProcesadoMistal(cintaAuditoraDto.getFechaProcesado());
            return guardarAnalisis(cintaAuditoraProcesadaDto)
                    ? cintaAuditoraProcesadaDto
                    : null;
        } catch (FeignException feignException) {
            throw new FeignClientException(feignException);
        }
    }

    private boolean guardarAnalisis(CintaAuditoraProcesadaDto cintaAuditoraProcesada) {
        TiendaFisica tienda = tiendaService.buscarTiendaFisica(cintaAuditoraProcesada.getIdTienda());
        CajaRegistradora cajaRegistradora = cajaRegistradoraService.buscarCajaRegistradora(tienda, cintaAuditoraProcesada);
        CintaAuditora cintaAuditora = buscarCintaAuditora(cajaRegistradora, cintaAuditoraProcesada);
        boolean result = false;
        for (DiaOperacionDto diaOperacionDto : cintaAuditoraProcesada.getDiaOperacions()) {
            DiaOperacion diaOperacion = diaOperacionService.buscarDiaOperacion(diaOperacionDto, cajaRegistradora, cintaAuditora);
            if (diaOperacion.getTransaccionesPagos() == null || diaOperacion.getTransaccionesPagos().isEmpty()) {
                productoDiaOPeracionCajaService.procesarProductosDiaOperacion(diaOperacionDto.getProductos(), diaOperacion);
                transaccionPagoService.procesarTransaccionPago(diaOperacionDto.getFormasPago(), diaOperacion);
                transaccionPagoService.procesarPropinas(diaOperacionDto.getPropinas(), diaOperacion);
                cajeroService.procesarCajerosDiaOperacion(diaOperacionDto.getCajeros(), diaOperacion, cajaRegistradora);
                result = true;
            }
        }
        return result;
    }

    private boolean cintaFueAnalizada(CintaAuditoraDto cintaAuditoraDto) {
        return cintaAuditoraRepository.existsCintaAuditora(cintaAuditoraDto.getIdTienda(),
                cintaAuditoraDto.getIdCaja(), cintaAuditoraDto.getCodigoAlmacen(), cintaAuditoraDto.getCodigoRed(),
                DateUtil.convertirLocalDateToDate(cintaAuditoraDto.getFechaCreacion()),
                DateUtil.convertirLocalDateToDate(cintaAuditoraDto.getFechaProcesado()));
    }

    private CintaAuditora buscarCintaAuditora(CajaRegistradora cajaRegistradora, CintaAuditoraProcesadaDto cintaAuditoraProcesadas) {
        CintaAuditora cintaAuditora = cintaAuditoraISingleton.findFirst(
                c -> c.getFechaCreacion().equals(DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesadas.getFechaCreacion()))
                        && c.getFechaHaladoVenta().equals(DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesadas.getFechaHaladoVenta()))
                        && c.getCajaRegistradora().getId().equals(cajaRegistradora.getId())
        );

        if (cintaAuditora == null) {
            cintaAuditora = cintaAuditoraRepository.findByFechaCreacionAndFechaHaladoVentaAndCajaRegistradora(
                            DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesadas.getFechaCreacion()),
                            DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesadas.getFechaHaladoVenta()),
                            cajaRegistradora)
                    .orElseGet(() -> createAndSaveNewCintaAuditora(cajaRegistradora, cintaAuditoraProcesadas));
            cintaAuditoraISingleton.add(cintaAuditora);
        }
        return cintaAuditora;
    }

    private CintaAuditora createAndSaveNewCintaAuditora(CajaRegistradora cajaRegistradora, CintaAuditoraProcesadaDto cintaAuditoraProcesada) {
        CintaAuditora cintaAuditora = new CintaAuditora();
        cintaAuditora.setByteSize(cintaAuditoraProcesada.getByteSize());
        cintaAuditora.setCountLines(cintaAuditoraProcesada.getCountLines());
        cintaAuditora.setFechaAnalisis(DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesada.getFechaAnalisis()));
        cintaAuditora.setFechaCreacion(DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesada.getFechaCreacion()));
        cintaAuditora.setFechaHaladoVenta(DateUtil.convertirLocalDateTimeToTimestamp(cintaAuditoraProcesada.getFechaHaladoVenta()));
        cintaAuditora.setPaperSize(cintaAuditoraProcesada.getPaperSizeCentimetros());
        cintaAuditora.setFechaCreacionMistal(DateUtil.convertirLocalDateToDate(cintaAuditoraProcesada.getFechaCreacionMistal()));
        cintaAuditora.setFechaProcesadoMistal(DateUtil.convertirLocalDateToDate(cintaAuditoraProcesada.getFechaProcesadoMistal()));
        cintaAuditora.setCajaRegistradora(cajaRegistradora);
        return cintaAuditoraRepository.save(cintaAuditora);
    }
}