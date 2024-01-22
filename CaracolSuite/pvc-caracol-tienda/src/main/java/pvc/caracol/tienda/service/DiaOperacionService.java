package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.common.utils.DateUtil;
import pvc.caracol.tienda.http.input.DiaOperacionDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.CintaAuditora;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.repository.IDiaOperacionRepository;
import pvc.caracol.tienda.service.interfaces.IDiaOperacionService;

import java.time.LocalDate;

@Service
public class DiaOperacionService extends BaseService implements IDiaOperacionService {
    private final IDiaOperacionRepository diaOperacionRepository;

    @Autowired
    public DiaOperacionService(IDiaOperacionRepository diaOperacionRepository) {
        this.diaOperacionRepository = diaOperacionRepository;
    }

    @Override
    public DiaOperacion buscarDiaOperacion(DiaOperacionDto diaOperacionDto, CajaRegistradora cajaRegistradora, CintaAuditora cintaAuditora) {
        DiaOperacion diaOperacion = buscarDiaOperacionByFechaAndCaja(diaOperacionDto.getFecha(), cajaRegistradora);
        return (diaOperacion == null) ?
                createAndSaveNewDiaOperacion(diaOperacionDto, cajaRegistradora, cintaAuditora)
                : diaOperacion;
    }

    @Override
    public DiaOperacion buscarDiaOperacionByFechaAndCaja(LocalDate fecha, CajaRegistradora cajaRegistradora) {
        return diaOperacionRepository.findByFechaAndCajaRegistradora(fecha, cajaRegistradora).orElse(null);
    }

    private DiaOperacion createAndSaveNewDiaOperacion(DiaOperacionDto diaOperacionDto, CajaRegistradora cajaRegistradora, CintaAuditora cintaAuditora) {
        DiaOperacion diaOperacion = new DiaOperacion();
        diaOperacion.setFecha(DateUtil.convertirLocalDateToDate(diaOperacionDto.getFecha()));
        diaOperacion.setCajaRegistradora(cajaRegistradora);
        diaOperacion.setCintaAuditora(cintaAuditora);
        return diaOperacionRepository.save(diaOperacion);
    }
}
