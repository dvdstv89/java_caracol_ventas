package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.CajeroDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.Cajero;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;
import pvc.caracol.tienda.repository.ICajeroRepository;
import pvc.caracol.tienda.service.interfaces.ICajeroService;
import pvc.caracol.tienda.service.interfaces.IRegistroOperacionesCajerosService;
import pvc.caracol.tienda.service.interfaces.IVentaCajeroService;
import pvc.caracol.tienda.singleton.ISingletonList;

import java.util.List;

@Service
public class CajeroService extends BaseService implements ICajeroService {
    private final ICajeroRepository cajeroRepository;
    private final IVentaCajeroService ventaCajeroService;
    private final IRegistroOperacionesCajerosService registroOperacionesCajerosService;
    private ISingletonList<Cajero> cajeroISingleton;

    @Autowired
    public CajeroService(ICajeroRepository cajeroRepository, IRegistroOperacionesCajerosService registroOperacionesCajerosService, IVentaCajeroService ventaCajeroService, ISingletonList<Cajero> cajeroISingleton) {
        this.cajeroRepository = cajeroRepository;
        this.cajeroISingleton = cajeroISingleton;
        this.registroOperacionesCajerosService = registroOperacionesCajerosService;
        this.ventaCajeroService = ventaCajeroService;
    }

    private Cajero buscarCajero(CajeroDto cajeroDto, CajaRegistradora cajaRegistradora) {
        Cajero cajero = cajeroISingleton.findFirst(
                c -> c.getCodigoCajero().equals(cajeroDto.getCodigo())
                        && c.getCajaRegistradora().equals(cajaRegistradora)
        );
        if (cajero == null) {
            cajero = cajeroRepository.findByCodigoCajeroAndCajaRegistradora(cajeroDto.getCodigo(), cajaRegistradora).orElseGet(() -> createAndSaveNewCajero(cajeroDto, cajaRegistradora));
            cajeroISingleton.add(cajero);
        }
        return cajero;
    }

    private Cajero createAndSaveNewCajero(CajeroDto cajeroDto, CajaRegistradora cajaRegistradora) {
        Cajero cajero = new Cajero();
        cajero.setCodigoCajero(cajeroDto.getCodigo());
        cajero.setCajaRegistradora(cajaRegistradora);
        return cajeroRepository.save(cajero);
    }

    @Override
    public void procesarCajerosDiaOperacion(List<CajeroDto> cajeroDtos, DiaOperacion diaOperacion, CajaRegistradora cajaRegistradora) {
        for (CajeroDto cajeroDto : cajeroDtos) {
            Cajero cajero = buscarCajero(cajeroDto, cajaRegistradora);
            RegistroOperacionesCajero registroOperacionesCajeros = registroOperacionesCajerosService.buscarRegistroOperacionesCajeros(diaOperacion, cajero, cajeroDto);
            ventaCajeroService.procesarTransaccionPago(cajeroDto.getVentas(), registroOperacionesCajeros);
            ventaCajeroService.procesarPropinas(cajeroDto.getPropinas(), registroOperacionesCajeros);
        }
    }
}
