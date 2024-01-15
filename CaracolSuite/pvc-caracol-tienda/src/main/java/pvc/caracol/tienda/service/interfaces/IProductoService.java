package pvc.caracol.tienda.service.interfaces;

import pvc.caracol.tienda.http.input.ProductoDto;
import pvc.caracol.tienda.model.Producto;

public interface IProductoService {
    Producto buscarProducto(ProductoDto productoDto);
}