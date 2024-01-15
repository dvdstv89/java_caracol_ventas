package pvc.caracol.cinad.mapper;

import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.dtos.ProductoDto;
import pvc.caracol.common.mapper.ObjectMapper;

public class ProductoMapper implements ObjectMapper<ProductoDto, VentaProducto> {
    @Override
    public ProductoDto map(VentaProducto ventaProducto) {
        return ProductoDto.builder()
                .code(ventaProducto.getProducto().getCode())
                .name(ventaProducto.getProducto().getName())
                .precioUnitario(ventaProducto.getProducto().getPrecioUnitario())
                .isInsumo(ventaProducto.getProducto().getIsInsumo())
                .cantidad(ventaProducto.getCantidad())
                .saldo(ventaProducto.getSaldo())
                .build();
    }
}
