package pvc.caracol.cinad.mapper;

import pvc.caracol.cinad.dtos.CajeroDto;
import pvc.caracol.cinad.models.Cajero;
import pvc.caracol.common.mapper.ObjectMapper;

public class CajeroMapper implements ObjectMapper<CajeroDto, Cajero> {
    @Override
    public CajeroDto map(Cajero cajero) {
        return CajeroDto.builder()
                .codigo(cajero.getCodigo())
                .haceFuncionSupervisor(cajero.isHaceFuncionSupervisor())
                .cantidadCorrecciones(cajero.getCantidadCorrecciones())
                .cantidadTVOID(cajero.getCantidadTVOID())
                .cantidadRFUND(cajero.getCantidadRFUND())
                .cantidadClientes(cajero.getCantidadClientes())
                .cantidadReportes(cajero.getCantidadReportes())
                .cantidadProductos(cajero.getCantidadProductos())
                .cantidadInsumos(cajero.getCantidadInsumos())
                .build();
    }
}
