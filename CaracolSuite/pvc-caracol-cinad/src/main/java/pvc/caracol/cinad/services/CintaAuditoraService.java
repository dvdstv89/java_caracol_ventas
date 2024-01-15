package pvc.caracol.cinad.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pvc.caracol.cinad.analizador.lexico.ILexer;
import pvc.caracol.cinad.analizador.lexico.Lexer;
import pvc.caracol.cinad.analizador.sintactico.IParser;
import pvc.caracol.cinad.analizador.sintactico.Parser;
import pvc.caracol.cinad.analizador.sintactico.models.VentaProducto;
import pvc.caracol.cinad.dtos.*;
import pvc.caracol.cinad.mapper.CajeroMapper;
import pvc.caracol.cinad.mapper.ProductoMapper;
import pvc.caracol.cinad.models.Cajero;
import pvc.caracol.cinad.models.CintaAuditoraElectronica;
import pvc.caracol.cinad.models.DiaOperacion;
import pvc.caracol.cinad.models.FicheroCintaAuditora;
import pvc.caracol.common.reponse.ApiWebResponse;
import pvc.caracol.common.service.BaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    private final IFicheroService ficheroService;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ModelMapper modelMapper) {
        this.ficheroService = new FicheroService();
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiWebResponse analizarCintaAuditora(byte[] fichero) throws Exception {
        FicheroCintaAuditora ficheroCintaAuditora = ficheroService.decodificarFichero(fichero);
        ILexer lexer = new Lexer(ficheroCintaAuditora.getSourceStream());
        IParser parser = new Parser(lexer);
        CintaAuditoraElectronica cintaAuditoraElectronica = parser.parse();
        CintaAuditoraProcesadaDto cintaAuditoraProcesadaDto = converToCintaAuditoraDto(ficheroCintaAuditora, cintaAuditoraElectronica);
        response.addOkResponse200(cintaAuditoraProcesadaDto);
        return response;
    }

    private CintaAuditoraProcesadaDto converToCintaAuditoraDto(FicheroCintaAuditora ficheroCintaAuditora, CintaAuditoraElectronica cintaAuditoraElectronica) {
        CintaAuditoraProcesadaDto cintaAuditoraProcesadaDto = CintaAuditoraProcesadaDto
                .builder()
                .name(ficheroCintaAuditora.getName())
                .byteSize(ficheroCintaAuditora.getByteSize())
                .fechaCreacion(ficheroCintaAuditora.getFechaCreacion())
                .paperSizeCentimetros(ficheroCintaAuditora.getPaperSizeCentimetros())
                .paperSizeMetros(ficheroCintaAuditora.getPaperSizeMetros())
                .countLines(ficheroCintaAuditora.getCountLines())
                .nombrePuntoVenta(cintaAuditoraElectronica.getNombrePuntoVenta())
                .fechaHaladoVenta(cintaAuditoraElectronica.getFechaHaladoVenta())
                .fechaAnalisis(LocalDateTime.now())
                .diaOperacions(new ArrayList<>())
                .build();

        for (DiaOperacion dia : cintaAuditoraElectronica.getDiaOperacions()) {
            DiaOperacionDto diaOperacionDto = new DiaOperacionDto();
            diaOperacionDto.setCodigoDiaOperacion(dia.getCodigoDiaOperacion());
            diaOperacionDto.setFecha(dia.getFecha());

            List<PagoDto> formasPago = dia.getFormasPago().stream()
                    .map(e -> modelMapper.map(e, PagoDto.class))
                    .toList();
            diaOperacionDto.setFormasPago(formasPago);

            List<PagoDto> propinas = dia.getPropinas().stream()
                    .map(e -> modelMapper.map(e, PagoDto.class))
                    .toList();
            diaOperacionDto.setPropinas(propinas);

            List<CajeroDto> cajeros = dia.getCajeros().stream()
                    .map(this::mapCajero)
                    .toList();
            diaOperacionDto.setCajeros(cajeros);


            List<ProductoDto> productos = dia.getProductos().stream()
                    .map(this::mapProducto)
                    .toList();
            diaOperacionDto.setProductos(productos);

            List<ProductoDto> devoluciones = dia.getDevoluciones().stream()
                    .map(this::mapProducto)
                    .toList();
            diaOperacionDto.setDevoluciones(devoluciones);
            cintaAuditoraProcesadaDto.getDiaOperacions().add(diaOperacionDto);
        }
        return cintaAuditoraProcesadaDto;
    }

    private CajeroDto mapCajero(Cajero cajero) {
        List<PagoDto> ventas = cajero.getVentas().stream()
                .map(e -> modelMapper.map(e, PagoDto.class))
                .toList();
        List<PagoDto> propinas = cajero.getPropinas().stream()
                .map(e -> modelMapper.map(e, PagoDto.class))
                .toList();
        CajeroDto cajeroDto = new CajeroMapper().map(cajero);
        cajeroDto.setVentas(ventas);
        cajeroDto.setPropinas(propinas);
        return cajeroDto;
    }

    private ProductoDto mapProducto(VentaProducto ventaProducto) {
        return new ProductoMapper().map(ventaProducto);
    }
}
