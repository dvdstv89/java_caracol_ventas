package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.CajeroDto;
import pvc.caracol.tienda.model.Cajero;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.RegistroOperacionesCajero;

public interface IRegistroOperacionesCajerosService {
    RegistroOperacionesCajero buscarRegistroOperacionesCajeros(DiaOperacion diaOperacion, Cajero cajero, CajeroDto cajeroDto);
}