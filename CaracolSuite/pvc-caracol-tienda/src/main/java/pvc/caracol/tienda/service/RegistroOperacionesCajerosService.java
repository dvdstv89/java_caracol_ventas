package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.CajeroDto;
import pvc.caracol.tienda.model.Cajero;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;
import pvc.caracol.tienda.repository.IRegistroOperacionesCajeroRepository;
import pvc.caracol.tienda.service.interfaces.IRegistroOperacionesCajerosService;

@Service
public class RegistroOperacionesCajerosService extends BaseService implements IRegistroOperacionesCajerosService {
    private final IRegistroOperacionesCajeroRepository registroOperacionesCajeroRepository;


    @Autowired
    public RegistroOperacionesCajerosService(IRegistroOperacionesCajeroRepository registroOperacionesCajeroRepository) {
        this.registroOperacionesCajeroRepository = registroOperacionesCajeroRepository;
    }

    @Override
    public RegistroOperacionesCajero buscarRegistroOperacionesCajeros(DiaOperacion diaOperacion, Cajero cajero, CajeroDto cajeroDto) {
        return registroOperacionesCajeroRepository.findByDiaOperacionAndCajero(diaOperacion, cajero).orElseGet(() -> createAndSaveNewCajero(diaOperacion, cajero, cajeroDto));
    }

    private RegistroOperacionesCajero createAndSaveNewCajero(DiaOperacion diaOperacion, Cajero cajero, CajeroDto cajeroDto) {
        RegistroOperacionesCajero registroOperacionesCajero = new RegistroOperacionesCajero();
        registroOperacionesCajero.setDiaOperacion(diaOperacion);
        registroOperacionesCajero.setCajero(cajero);
        registroOperacionesCajero.setHaceFuncionSupervisor(cajeroDto.isHaceFuncionSupervisor());
        registroOperacionesCajero.setCantidadCorrecciones(cajeroDto.getCantidadCorrecciones());
        registroOperacionesCajero.setCantidadTVOID(cajeroDto.getCantidadTVOID());
        registroOperacionesCajero.setCantidadRFUND(cajeroDto.getCantidadRFUND());
        registroOperacionesCajero.setCantidadClientes(cajeroDto.getCantidadClientes());
        registroOperacionesCajero.setCantidadReportes(cajeroDto.getCantidadReportes());
        registroOperacionesCajero.setCantidadProductos(cajeroDto.getCantidadProductos());
        registroOperacionesCajero.setCantidadInsumos(cajeroDto.getCantidadInsumos());
        return registroOperacionesCajeroRepository.save(registroOperacionesCajero);
    }


}
