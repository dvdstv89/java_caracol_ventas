package pvc.caracol.tienda.mapper;

import pvc.caracol.common.mapper.ObjectMapper;
import pvc.caracol.tienda.dtos.CajaDto;
import pvc.caracol.tienda.http.output.CajaRegistradoraDto;
import pvc.caracol.tienda.http.input.ProcesarCintasTiendasDto;

public class CajaMapper implements ObjectMapper<CajaRegistradoraDto, CajaDto> {

    private final ProcesarCintasTiendasDto tiendaDto;

    public CajaMapper(ProcesarCintasTiendasDto tiendaDto) {
        this.tiendaDto = tiendaDto;
    }

    @Override
    public CajaRegistradoraDto map(CajaDto cajaDto) {
        return CajaRegistradoraDto.builder()
                .idCaja(cajaDto.getIdCaja())
                .codigoAlmacen(cajaDto.getCodigoAlmacen())
                .idTienda(tiendaDto.getCodigoTienda())
                .codigoRed(cajaDto.getCodigoRed())
                .idCentroGestion(cajaDto.getIdCentroGestion())
                .fechaInicio(tiendaDto.getFechaInicio())
                .fechaFin(tiendaDto.getFechaFin())
                .build();
    }
}
