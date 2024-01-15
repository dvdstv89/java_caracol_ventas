package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.DiaOperacionDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.CintaAuditora;
import pvc.caracol.tienda.model.DiaOperacion;

import java.time.LocalDate;

public interface IDiaOperacionService {
    DiaOperacion buscarDiaOperacion(DiaOperacionDto diaOperacionDto, CajaRegistradora cajaRegistradora, CintaAuditora cintaAuditora);

    DiaOperacion buscarDiaOperacionByFechaAndCaja(LocalDate fecha, CajaRegistradora cajaRegistradora);
}
