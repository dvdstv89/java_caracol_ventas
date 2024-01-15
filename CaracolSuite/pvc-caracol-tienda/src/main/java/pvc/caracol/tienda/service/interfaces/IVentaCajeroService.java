package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.PagoDto;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;

import java.util.List;

public interface IVentaCajeroService {
    void procesarTransaccionPago(List<PagoDto> formasPago, RegistroOperacionesCajero registroOperacionCajero);

    void procesarPropinas(List<PagoDto> propinas, RegistroOperacionesCajero registroOperacionCajero);
}