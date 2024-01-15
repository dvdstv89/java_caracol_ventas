package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.PagoDto;
import pvc.caracol.tienda.model.DiaOperacion;

import java.util.List;

public interface ITransaccionPagoService {
    void procesarTransaccionPago(List<PagoDto> formasPago, DiaOperacion diaOperacion);

    void procesarPropinas(List<PagoDto> propinas, DiaOperacion diaOperacion);
}