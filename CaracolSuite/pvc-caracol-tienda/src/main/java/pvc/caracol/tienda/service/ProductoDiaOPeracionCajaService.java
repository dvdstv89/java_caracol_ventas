package pvc.caracol.tienda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.common.service.BaseService;
import pvc.caracol.tienda.http.input.ProductoDto;
import pvc.caracol.tienda.model.DiaOperacion;
import pvc.caracol.tienda.model.Producto;
import pvc.caracol.tienda.model.ProductoDiaOperacionCaja;
import pvc.caracol.tienda.model.pk.ProductoDiaOperacionCajaId;
import pvc.caracol.tienda.repository.IProductoDiaOperacionCajaRepository;
import pvc.caracol.tienda.service.interfaces.IProductoDiaOPeracionCajaService;
import pvc.caracol.tienda.service.interfaces.IProductoService;

import java.util.List;

@Service
public class ProductoDiaOPeracionCajaService extends BaseService implements IProductoDiaOPeracionCajaService {
    private final IProductoDiaOperacionCajaRepository productoDiaOperacionCajaRepository;
    private final IProductoService productoService;

    @Autowired
    public ProductoDiaOPeracionCajaService(IProductoService productoService, IProductoDiaOperacionCajaRepository productoDiaOperacionCajaRepository) {
        this.productoDiaOperacionCajaRepository = productoDiaOperacionCajaRepository;
        this.productoService = productoService;
    }

    @Override
    public void procesarProductosDiaOperacion(List<ProductoDto> productos, DiaOperacion diaOperacion) {
        for (ProductoDto productoDto : productos) {
            buscarProductoDiaOperacionCaja(productoDto, diaOperacion);
        }
    }


    private ProductoDiaOperacionCaja buscarProductoDiaOperacionCaja(ProductoDto productoDto, DiaOperacion diaOperacion) {
        Producto producto = productoService.buscarProducto(productoDto);

        ProductoDiaOperacionCajaId productoDiaOperacionCajaId = new ProductoDiaOperacionCajaId();
        productoDiaOperacionCajaId.setDiaOperacion(diaOperacion.getId());
        productoDiaOperacionCajaId.setProducto(productoDto.getCode());

        ProductoDiaOperacionCaja productoDiaOperacionCaja = productoDiaOperacionCajaRepository.findById(productoDiaOperacionCajaId).orElse(null);

        if (productoDiaOperacionCaja == null) {
            productoDiaOperacionCaja = createAndSaveNewTienda(producto, productoDto, diaOperacion);
        } else {
            productoDiaOperacionCaja.setCantidad(productoDiaOperacionCaja.getCantidad() + productoDto.getCantidad());
            productoDiaOperacionCajaRepository.save(productoDiaOperacionCaja);
        }
        return productoDiaOperacionCaja;
    }

    private ProductoDiaOperacionCaja createAndSaveNewTienda(Producto producto, ProductoDto productoDto, DiaOperacion diaOperacion) {
        ProductoDiaOperacionCaja productoDiaOperacionCaja = new ProductoDiaOperacionCaja();
        productoDiaOperacionCaja.setProducto(producto);
        productoDiaOperacionCaja.setCantidad(productoDto.getCantidad());
        productoDiaOperacionCaja.setDiaOperacion(diaOperacion);
        return productoDiaOperacionCajaRepository.save(productoDiaOperacionCaja);
    }
}
