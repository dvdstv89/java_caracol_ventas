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
import pvc.caracol.cinad.http.CintaAuditoraDto;
import pvc.caracol.cinad.models.*;
import pvc.caracol.common.reponse.ApiResponse;
import pvc.caracol.common.service.BaseService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CintaAuditoraService extends BaseService implements ICintaAuditoraService {
    private final IFicheroService ficheroService;
    private final ModelMapper modelMapper;

    @Autowired
    public CintaAuditoraService(ModelMapper modelMapper) {
        this.ficheroService = new FicheroService();
        this.modelMapper = modelMapper;
    }

    public ApiResponse analizarCintaAuditora(CintaAuditoraDto cintaAuditoraDto) throws Exception {
        FicheroCintaAuditora ficheroCintaAuditora = ficheroService.decodificarFichero(cintaAuditoraDto.getFichero());
        ILexer lexer = new Lexer(ficheroCintaAuditora.getSourceStream());
        IParser parser = new Parser(lexer);
        CintaAuditoraElectronica cintaAuditoraElectronica = parser.parse();
        CintaAuditoraProcesadaDto cintaAuditoraProcesadaDto = converToCintaAuditoraDto(cintaAuditoraDto, ficheroCintaAuditora, cintaAuditoraElectronica);
        response.addOkResponse200(cintaAuditoraProcesadaDto);
        return response;
    }

    private CintaAuditoraProcesadaDto converToCintaAuditoraDto(CintaAuditoraDto cintaAuditoraDto, FicheroCintaAuditora ficheroCintaAuditora, CintaAuditoraElectronica cintaAuditoraElectronica) {
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
                .idCaja(cintaAuditoraDto.getIdCaja())
                .codigoAlmacen(cintaAuditoraDto.getCodigoAlmacen())
                .codigoRed(cintaAuditoraDto.getCodigoRed())
                .diaOperacions(new ArrayList<>())
                .build();

        for (DiaOperacion dia: cintaAuditoraElectronica.getDiaOperacions()){
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


            List<ProductoDto> productos =  dia.getProductos().stream()
                    .map(this::mapProducto)
                    .toList();
            diaOperacionDto.setProductos(productos);

            List<ProductoDto> devoluciones =  dia.getDevoluciones().stream()
                    .map(this::mapProducto)
                    .toList();
            diaOperacionDto.setDevoluciones(devoluciones);
            cintaAuditoraProcesadaDto.getDiaOperacions().add(diaOperacionDto);
        }
        return cintaAuditoraProcesadaDto;
    }

    private CajeroDto mapCajero(Cajero cajero){
        List<PagoDto> ventas = cajero.getVentas().stream()
                .map(e -> modelMapper.map(e, PagoDto.class))
                .toList();
        return CajeroDto
                .builder()
                .ventas(ventas)
                .codigo(cajero.getCodigo())
                .haceFuncionSupervisor(cajero.isHaceFuncionSupervisor())
                .cantidadCorrecciones(cajero.getCantidadCorrecciones())
                .cantidadTVOID(cajero.getCantidadTVOID())
                .cantidadRFUND(cajero.getCantidadRFUND())
                .cantidadClientes(cajero.getCantidadClientes())
                .cantidadReportes(cajero.getCantidadReportes())
                .propina(cajero.getPropina())
                .cantidadProductos(cajero.getCantidadProductos())
                .cantidadInsumos(cajero.getCantidadInsumos())
                .build();
    }

    private ProductoDto mapProducto(VentaProducto ventaProducto){
        return ProductoDto
                .builder()
                .code(ventaProducto.getProducto().getCode())
                .name(ventaProducto.getProducto().getName())
                .precioUnitario(ventaProducto.getProducto().getPrecioUnitario())
                .isInsumo(ventaProducto.getProducto().getIsInsumo())
                .cantidad(ventaProducto.getCantidad())
                .saldo(ventaProducto.getSaldo())
                .build();
    }
}
