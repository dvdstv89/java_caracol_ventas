package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.ProductoDto;
import pvc.caracol.tienda.model.DiaOperacion;

import java.util.List;

public interface IProductoDiaOPeracionCajaService {
    void procesarProductosDiaOperacion(List<ProductoDto> productos, DiaOperacion diaOperacion);
}