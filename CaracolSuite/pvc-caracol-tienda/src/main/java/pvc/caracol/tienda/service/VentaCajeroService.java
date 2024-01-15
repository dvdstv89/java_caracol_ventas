package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.PagoDto;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;
import pvc.caracol.tienda.model.VentaCajero;
import pvc.caracol.tienda.repository.IVentaCajeroRepository;
import pvc.caracol.tienda.service.interfaces.IVentaCajeroService;

import java.util.List;

@Service
public class VentaCajeroService extends BaseService implements IVentaCajeroService {
    private final IVentaCajeroRepository ventaCajeroRepository;

    @Autowired
    public VentaCajeroService(IVentaCajeroRepository ventaCajeroRepository) {
        this.ventaCajeroRepository = ventaCajeroRepository;
    }

    @Override
    public void procesarTransaccionPago(List<PagoDto> formasPago, RegistroOperacionesCajero registroOperacionCajero) {
        for (PagoDto pago : formasPago) {
            buscarVentaCajero(pago, registroOperacionCajero, false);
        }
    }

    @Override
    public void procesarPropinas(List<PagoDto> propinas, RegistroOperacionesCajero registroOperacionCajero) {
        for (PagoDto pago : propinas) {
            buscarVentaCajero(pago, registroOperacionCajero, true);
        }
    }

    private VentaCajero buscarVentaCajero(PagoDto pago, RegistroOperacionesCajero registroOperacionCajero, Boolean propina) {
        VentaCajero ventaCajero = ventaCajeroRepository.findByFormaPagoAndRegistroOperacionCajeroAndPropina(pago.getFormaPago(), registroOperacionCajero, propina)
                .orElse(null);

        if (ventaCajero == null) {
            ventaCajero = createAndSaveNewVentaCajero(pago, registroOperacionCajero, propina);
        } else {
            ventaCajero.setSaldo(ventaCajero.getSaldo() + pago.getPagado());
            ventaCajeroRepository.save(ventaCajero);
        }
        return ventaCajero;
    }

    private VentaCajero createAndSaveNewVentaCajero(PagoDto pago, RegistroOperacionesCajero registroOperacionesCajero, Boolean propina) {
        VentaCajero ventaCajero = new VentaCajero();
        ventaCajero.setSaldo(pago.getPagado());
        ventaCajero.setFormaPago(pago.getFormaPago());
        ventaCajero.setPropina(propina);
        ventaCajero.setRegistroOperacionCajero(registroOperacionesCajero);
        return ventaCajeroRepository.save(ventaCajero);
    }
}
