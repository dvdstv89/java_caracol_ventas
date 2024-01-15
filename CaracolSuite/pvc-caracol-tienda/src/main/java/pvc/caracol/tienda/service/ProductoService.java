package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.ProductoDto;
import pvc.caracol.tienda.model.Producto;
import pvc.caracol.tienda.repository.IProductoRepository;
import pvc.caracol.tienda.service.interfaces.IProductoService;
import pvc.caracol.tienda.singleton.ISingletonList;

@Service
public class ProductoService extends BaseService implements IProductoService {
    private final IProductoRepository productoRepository;
    private ISingletonList<Producto> productoISingleton;

    @Autowired
    public ProductoService(IProductoRepository productoRepository, ISingletonList<Producto> productoISingleton) {
        this.productoRepository = productoRepository;
        this.productoISingleton = productoISingleton;
    }

    @Override
    public Producto buscarProducto(ProductoDto productoDto) {
        Producto producto = productoISingleton.findFirst(t -> t.getCode().equals(productoDto.getCode()));
        if (producto == null) {
            producto = productoRepository.findById(productoDto.getCode()).orElseGet(() -> createAndSaveNewProducto(productoDto));
            productoISingleton.add(producto);
        }
        return producto;
    }

    private Producto createAndSaveNewProducto(ProductoDto productoDto) {
        Producto producto = new Producto();
        producto.setCode(productoDto.getCode());
        producto.setName(productoDto.getName());
        producto.setPrecio(productoDto.getPrecioUnitario());
        return productoRepository.save(producto);
    }
}
