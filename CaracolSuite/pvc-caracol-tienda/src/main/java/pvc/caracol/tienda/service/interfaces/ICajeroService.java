package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.CajeroDto;
import pvc.caracol.tienda.model.CajaRegistradora;
import pvc.caracol.tienda.model.DiaOperacion;

import java.util.List;

public interface ICajeroService {

    void procesarCajerosDiaOperacion(List<CajeroDto> cajeroDtos, DiaOperacion diaOperacion, CajaRegistradora cajaRegistradora);
}