package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DiaOperacionDto {
    private String codigoDiaOperacion;
    private LocalDate fecha;
    private List<PagoDto> formasPago;
    private List<PagoDto> propinas;
    private List<ProductoDto> productos;
    private List<CajeroDto> cajeros;
    private List<ProductoDto> devoluciones;
}