package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.PagoDto;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.TransaccionPago;
import pvc.caracol.tienda.repository.ITransaccionPagoRepository;
import pvc.caracol.tienda.service.interfaces.ITransaccionPagoService;

import java.util.List;

@Service
public class TransaccionPagoService extends BaseService implements ITransaccionPagoService {
    private final ITransaccionPagoRepository transaccionPagoRepository;


    @Autowired
    public TransaccionPagoService(ITransaccionPagoRepository transaccionPagoRepository) {
        this.transaccionPagoRepository = transaccionPagoRepository;
    }

    @Override
    public void procesarTransaccionPago(List<PagoDto> formasPago, DiaOperacion diaOperacion) {
        for (PagoDto pago : formasPago) {
            buscarTransaccionPago(pago, diaOperacion, false);
        }
    }

    @Override
    public void procesarPropinas(List<PagoDto> propinas, DiaOperacion diaOperacion) {
        for (PagoDto pago : propinas) {
            buscarTransaccionPago(pago, diaOperacion, true);
        }
    }


    private TransaccionPago buscarTransaccionPago(PagoDto pago, DiaOperacion diaOperacion, Boolean propina) {
        TransaccionPago transaccionPago = transaccionPagoRepository.findByFormaPagoAndDiaOperacionAndPropina(pago.getFormaPago(), diaOperacion, propina)
                .orElse(null);

        if (transaccionPago == null) {
            transaccionPago = createAndSaveNewTransaccionPago(pago, diaOperacion, propina);
        } else {
            transaccionPago.setSaldo(transaccionPago.getSaldo() + pago.getPagado());
            transaccionPagoRepository.save(transaccionPago);
        }
        return transaccionPago;
    }

    private TransaccionPago createAndSaveNewTransaccionPago(PagoDto pago, DiaOperacion diaOperacion, Boolean propina) {
        TransaccionPago transaccionPago = new TransaccionPago();
        transaccionPago.setSaldo(pago.getPagado());
        transaccionPago.setFormaPago(pago.getFormaPago());
        transaccionPago.setPropina(propina);
        transaccionPago.setDiaOperacion(diaOperacion);
        return transaccionPagoRepository.save(transaccionPago);
    }
}
