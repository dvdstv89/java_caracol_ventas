package pvc.caracol.cinad.analizador.lexico.tokens.enums;

public enum TokenKind {
    tk_CODIGO_REPORTE, // "lexema": "X001279-0002"
    tk_FECHA,// "lexema": "28-12-21 2  7:09P"
    tk_CODIGO_PRODUCTO,// "lexema": "02000002254935"
    tk_ARTICULOS,//"lexema": "Art�culos",
    tk_REPORTE_PLU,//"lexema": "Reporte Plu"
    tk_TOTAL_SECCION,// "lexema": "*TOTAL SECCION *"
    tk_FINAL_CINTA,  //"lexema": "Finalizado !!"
    tk_HORA, //"lexema": "8:35:13 AM"
    Tk_FECHA_SIMPLE, //"lexema": "Mi�rcoles 29-12-2021"
    tk_DECLARACION_FORMAS_PAGO, //"lexema": "Declaraci�n"
    tk_FORMAS_PAGO, //"lexema": "CUP", "lexema": "ENZONA MLC",
    tk_FORMAS_PAGO_ESPECIAL, //"lexema": "CUP", "lexema": "ENZONA MLC",
    tk_LITERAL_DOUBLE,//"lexema": "0.00",
    tk_REPORTE_DESCONOCIDO,// "liena vacia"
    tk_AUDITORA_ELECTRONICA,//"lexema": "Auditora Electr�nica"
    tk_CHECKING_PLU_FILE,// "lexema": "Checking Plu File"
    tk_X,// "lexema": "X"
    tk_NOMBRE_PUNTO_VENTA,// "lexema": "***  HABANA LIBRE ***"
    tk_MEMORY_USED,//"lexema": "0.01%"
    tk_FOOTER,//"lexema": "#000462 003 001"
    tk_NOMBRE_CAJERO,// "lexema": "@MARIDELVIS"
    tk_NUMERO_TC,//"lexema": "N�mero de TC
    tk_CODE_TC,//"lexema": "900115151"
    tk_DECLARADO,// "lexema": "Declarado"
    tk_NRGT_POSITIVE,//   "lexema": "(+)NRGT",
    tk_NRGT_NEGATIVE,//   "lexema": "(+)NRGT",
    tk_Moneda,
    tk_SESSION_FORMAS_PAGO,
    tk_INTERRUMPIDO_USUARIO,
    tk_PROPINA,
    tk_ENCENDER_CAJA,
    tk_EXTRACCION_FONDO,
    tk_FONDO,
    tk_ENTRADA_FONDO,
    tk_EXTRACCION_CUP,
    tk_REFUND,
    tk_TVOID,
    tk_CADENA_TEXTO,//NOMBRE DEL PRODUTO
    tk_LITERAL_ENTERO,//CODIGO DEL CAJERO
    tk_CODIGO_CAJERO,//ok
    tk_CODIGO_OPERACION,//ok
    tk_MODO_PROGRAMACION,//ok
    tk_Cntd_X,
    tk_CODE_FINAL_CINTA,
    tk_CREADO,//ok
    tk_REPORTE_TERMINAL,//ok
    tk_REPORTE_CAJERO,//ok
    tk_SUBTOTAL,//ok
    tk_TOTAL,//ok
    tk_VENTA_NETA,//ok
    tk_PAGADO,//ok
    tk_PAGO,//ok
    tk_DIA_SEMANA,//ok
    tk_CAMBIO,//ok
    tk_DEBE,
    tk_CANTIDAD,//ok//CANTIDAD DE PRODUCTO
    tk_IMPORTE,//ok
    tk_NOMBRE_PRODUCTO,//ok
    tk_TOTAL_VENTAS,
    tk_VENTA_TOTAL,
    tk_CLIENTES,
    tk_EC,
    tk_VOID,
    tk_TOTALES_CAJA,
    tk_CORRECCIONES,
    tk_R_ENCENDIDO,
    tk_INTERRUMPIDO_POR_USUARIO,
    tk_REPORTE_BORRADO,
    tk_ERROR,
    tk_DIFERENCIA,
    tk_DIA_OPERACION,
    tk_EOT//ok
}
