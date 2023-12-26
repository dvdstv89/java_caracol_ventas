package pvc.caracol.common.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public enum FormaPago {
    CUC("CUC", TipoMoneda.CUC),
    MLC("MLC", TipoMoneda.MLC),
    CUP("CUP", TipoMoneda.CUP),
    TC_NAC("Tarjeta de Credito Nacional", TipoMoneda.CUP),
    TC_CUC("Tarjeta de Credito CUC", TipoMoneda.CUC),
    TC_INT("Tarjeta de Credito Internacional", TipoMoneda.MLC),
    MLC_NAC("Tarjeta de MLC Nacional", TipoMoneda.MLC),
    MLC_INT("Tarjeta de MLC Internacional", TipoMoneda.MLC),
    CHEQUE("Cheque", TipoMoneda.CUP),
    BIDAIONDO("BIDAIONDO", TipoMoneda.USD),
    ENZONA_MLC("Enzona MLC", TipoMoneda.MLC),
    ENZONA_CUP("Enzona CUP", TipoMoneda.CUP),
    TRANSFER_MLC("Transfermovil MLC", TipoMoneda.MLC),
    TRANSFER_CUP("Transfermovil CUP", TipoMoneda.CUP),
    FONDO("Fondo", TipoMoneda.CUP),
    EFECTIVO("Efectivo", TipoMoneda.CUP);

    private final TipoMoneda tipoMoneda;
    private final String descripcion;

    FormaPago(String descripcion, TipoMoneda tipoMoneda) {
        this.descripcion = descripcion;
        this.tipoMoneda = tipoMoneda;
    }

    private static final Map<String, FormaPago> tipoMonedaToFormaPagoMap  = Arrays
            .stream(values())
            .collect(Collectors.toMap(FormaPago::getDescripcion, Function.identity()));

    @Override
    public String toString() {
        return descripcion;
    }
}
