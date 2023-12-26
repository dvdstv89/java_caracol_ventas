package pvc.caracol.cinad.analizador.lexico.tokens.enums;

import java.util.Map;

public class PalabrasReservadas {

    private final Map<String, TokenKind> reservedWords;
    private static volatile PalabrasReservadas instance;

    private PalabrasReservadas() {
        reservedWords = Map.ofEntries(
                Map.entry("Auditora Electrónica", TokenKind.tk_AUDITORA_ELECTRONICA),
                Map.entry("Auditora Electr�nica", TokenKind.tk_AUDITORA_ELECTRONICA),
                Map.entry("Finalizado !!", TokenKind.tk_FINAL_CINTA),
                Map.entry("Reporte Terminal", TokenKind.tk_REPORTE_TERMINAL),
                Map.entry("Reporte Cajero", TokenKind.tk_REPORTE_CAJERO),
                Map.entry("Reporte Plu", TokenKind.tk_REPORTE_PLU),
                Map.entry("Checking Plu File", TokenKind.tk_CHECKING_PLU_FILE),
                Map.entry("Artículos", TokenKind.tk_ARTICULOS),
                Map.entry("Art�culos", TokenKind.tk_ARTICULOS),
                Map.entry("Creado", TokenKind.tk_CREADO),
                Map.entry("Subtotal", TokenKind.tk_SUBTOTAL),
                Map.entry("Total", TokenKind.tk_TOTAL),
                Map.entry("Venta NETA", TokenKind.tk_VENTA_NETA),
                Map.entry("Pagado", TokenKind.tk_PAGADO),
                Map.entry("Cambio", TokenKind.tk_CAMBIO),
                Map.entry("Cantidad", TokenKind.tk_CANTIDAD),
                Map.entry("Importe", TokenKind.tk_IMPORTE),
                Map.entry("*TOTAL SECCION *", TokenKind.tk_TOTAL_SECCION),
                Map.entry("Declaración", TokenKind.tk_DECLARACION_FORMAS_PAGO),
                Map.entry("Declaraci�n", TokenKind.tk_DECLARACION_FORMAS_PAGO),
                Map.entry("Propina", TokenKind.tk_PROPINA),
                Map.entry("EC", TokenKind.tk_EC),
                Map.entry("Void", TokenKind.tk_VOID),
                Map.entry("TVoid", TokenKind.tk_TVOID),
                Map.entry("Refund", TokenKind.tk_REFUND),
                Map.entry("Interrumpido por Usuario", TokenKind.tk_INTERRUMPIDO_POR_USUARIO),
                Map.entry("Reporte BORRADO", TokenKind.tk_REPORTE_BORRADO),
                Map.entry("Diferencia", TokenKind.tk_DIFERENCIA),
                Map.entry("Extracción Fon", TokenKind.tk_EXTRACCION_FONDO),
                Map.entry("Extracci�n Fon", TokenKind.tk_EXTRACCION_FONDO),
                Map.entry("Entrada Fondo", TokenKind.tk_ENTRADA_FONDO),
                Map.entry("Extracción CUP", TokenKind.tk_EXTRACCION_CUP),
                Map.entry("Extracci�n CUP", TokenKind.tk_EXTRACCION_CUP),
                Map.entry("R Encendido", TokenKind.tk_ENCENDER_CAJA),
                Map.entry("TC/NAC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("TC/CUC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("CUP", TokenKind.tk_FORMAS_PAGO),
                Map.entry("CUC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("MLC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("MLC/NAC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("MLC/INT", TokenKind.tk_FORMAS_PAGO),
                Map.entry("Cheque", TokenKind.tk_FORMAS_PAGO),
                Map.entry("BIDAIONDO", TokenKind.tk_FORMAS_PAGO),
                Map.entry("TC/INT", TokenKind.tk_FORMAS_PAGO),
                Map.entry("ENZONA MLC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("ENZONA CUP", TokenKind.tk_FORMAS_PAGO),
                Map.entry("TRANSFER MLC", TokenKind.tk_FORMAS_PAGO),
                Map.entry("TRANSFER CUP", TokenKind.tk_FORMAS_PAGO),
                Map.entry("Efectivo", TokenKind.tk_FORMAS_PAGO),
                Map.entry("Fondo", TokenKind.tk_FONDO),
                Map.entry("TCr-USD", TokenKind.tk_FORMAS_PAGO_ESPECIAL),
                Map.entry("TC/USD", TokenKind.tk_FORMAS_PAGO),
                Map.entry("Pago", TokenKind.tk_PAGO),
                Map.entry("Lunes", TokenKind.tk_DIA_SEMANA),
                Map.entry("Martes", TokenKind.tk_DIA_SEMANA),
                Map.entry("Miércoles", TokenKind.tk_DIA_SEMANA),
                Map.entry("Mi�rcoles", TokenKind.tk_DIA_SEMANA),
                Map.entry("Jueves", TokenKind.tk_DIA_SEMANA),
                Map.entry("Viernes", TokenKind.tk_DIA_SEMANA),
                Map.entry("Sábado", TokenKind.tk_DIA_SEMANA),
                Map.entry("S�bado", TokenKind.tk_DIA_SEMANA),
                Map.entry("Domingo", TokenKind.tk_DIA_SEMANA),
                Map.entry("Totales en Caja", TokenKind.tk_TOTALES_CAJA),
                Map.entry("Modo Programación", TokenKind.tk_MODO_PROGRAMACION),
                Map.entry("Modo Programaci�n", TokenKind.tk_MODO_PROGRAMACION),
                Map.entry("Cntd X", TokenKind.tk_Cntd_X),
                Map.entry("Clientes", TokenKind.tk_CLIENTES),
                Map.entry("Venta Total", TokenKind.tk_VENTA_TOTAL),
                Map.entry("Total de Ventas", TokenKind.tk_TOTAL_VENTAS),
                Map.entry("Correcciones", TokenKind.tk_CORRECCIONES),
                Map.entry("Formas de Pagos", TokenKind.tk_SESSION_FORMAS_PAGO),
                Map.entry("Número de TC", TokenKind.tk_NUMERO_TC),
                Map.entry("N�mero de TC", TokenKind.tk_NUMERO_TC),
                Map.entry("Declarado", TokenKind.tk_DECLARADO),
                Map.entry("(+)NRGT", TokenKind.tk_NRGT_POSITIVE),
                Map.entry("(-)NRGT", TokenKind.tk_NRGT_NEGATIVE),
                Map.entry("Moneda", TokenKind.tk_Moneda),
                Map.entry("X", TokenKind.tk_X)
        );
    }

    private Map<String, TokenKind> getReservedWords() {
        return reservedWords;
    }

    public static TokenKind getToken(String word) {
        if (instance == null) {
            instance = new PalabrasReservadas();
        }
        String wordInUpperCase = word.toUpperCase();
        return instance.getReservedWords().entrySet()
                .stream()
                .filter(entry -> entry.getKey().toUpperCase().equals(wordInUpperCase))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(TokenKind.tk_CADENA_TEXTO);
    }

    public static String getTokenString(TokenKind tokenKind) {
        if (instance == null) {
            instance = new PalabrasReservadas();
        }
        for (Map.Entry<String, TokenKind> entry : instance.getReservedWords().entrySet()) {
            if (entry.getValue() == tokenKind) {
                return entry.getKey();
            }
        }
        return null;
    }
}
