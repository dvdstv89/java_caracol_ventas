package pvc.caracol.cinad.analizador.sintactico.operaciones.enums;

import pvc.caracol.common.enums.FormaPago;

import java.util.Map;

public class PalabrasReservadasFormasPago {
    private final Map<String, FormaPago> reservedWords;
    private static volatile PalabrasReservadasFormasPago instance;

    private PalabrasReservadasFormasPago() {
        reservedWords = Map.ofEntries(
                Map.entry("TC/NAC", FormaPago.TC_NAC),
                Map.entry("TC/CUC", FormaPago.TC_CUC),
                Map.entry("TC/USD", FormaPago.TC_INT),
                Map.entry("CUP", FormaPago.CUP),
                Map.entry("CUC", FormaPago.CUC),
                Map.entry("MLC", FormaPago.MLC),
                Map.entry("MLC/NAC", FormaPago.MLC_NAC),
                Map.entry("MLC/INT", FormaPago.MLC_INT),
                Map.entry("Cheque", FormaPago.CHEQUE),
                Map.entry("BIDAIONDO", FormaPago.BIDAIONDO),
                Map.entry("TC/INT", FormaPago.TC_INT),
                Map.entry("ENZONA MLC", FormaPago.ENZONA_MLC),
                Map.entry("ENZONA CUP", FormaPago.ENZONA_CUP),
                Map.entry("TRANSFER MLC", FormaPago.TRANSFER_MLC),
                Map.entry("TRANSFER CUP", FormaPago.TRANSFER_CUP),
                Map.entry("Efectivo", FormaPago.CUP)
        );
    }

    private Map<String, FormaPago> getReservedWords() {
        return reservedWords;
    }

    public static FormaPago getFormaPago(String word) {
        if (instance == null) {
            instance = new PalabrasReservadasFormasPago();
        }
        String wordInUpperCase = word.toUpperCase();
        return instance.getReservedWords().entrySet()
                .stream()
                .filter(entry -> entry.getKey().toUpperCase().equals(wordInUpperCase))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(FormaPago.EFECTIVO);
    }
}
