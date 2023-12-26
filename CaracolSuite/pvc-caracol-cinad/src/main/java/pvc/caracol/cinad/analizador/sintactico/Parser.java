package pvc.caracol.cinad.analizador.sintactico;

import pvc.caracol.cinad.analizador.lexico.ILexer;
import pvc.caracol.cinad.analizador.lexico.tokens.Token;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.CharType;
import pvc.caracol.cinad.analizador.lexico.tokens.enums.TokenKind;
import pvc.caracol.cinad.analizador.sintactico.operaciones.*;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoInstruccion;
import pvc.caracol.cinad.analizador.sintactico.operaciones.enums.TipoOperacion;
import pvc.caracol.cinad.models.CintaAuditoraElectronica;

import java.util.ArrayList;
import java.util.List;

public class Parser implements IParser {
    private ILexer lexer;
    private CintaAuditoraElectronica cintaAuditoraElectronica;
    private Token tokenActual;
    private List<Token> tokensOperacion;

    public Parser(ILexer lexer) {
        this.lexer = lexer;
        tokensOperacion = new ArrayList<>();
        cintaAuditoraElectronica = new CintaAuditoraElectronica();
    }

    private void consume() {
        tokenActual = lexer.nextToken();
        tokensOperacion.add(tokenActual);
    }

    private void consume(int count) {
        while (count > 0) {
            consume();
            count--;
        }
    }

    private String consumeAfterGetLexema() {
        String result = tokenActual.getLexema();
        consume();
        return result;
    }

    private boolean matchAndConsume(TokenKind tkExpected) {
        if (tokenActual.match(tkExpected)) {
            consume();
            return true;
        }
        return false;
    }

    private boolean matchNotConsume(TokenKind tkExpected) {
        return tokenActual.match(tkExpected);
    }

    private TipoInstruccion getTipoInstruccion() {
        return switch (tokenActual.getTokenKind()) {
            case TokenKind.tk_MODO_PROGRAMACION -> TipoInstruccion.MANTENIMIENTO_REMOTO;
            case TokenKind.tk_REPORTE_TERMINAL -> TipoInstruccion.REPORTE_TERMINAL;
            case TokenKind.tk_REPORTE_PLU -> TipoInstruccion.REPORTE_PLU;
            case TokenKind.tk_REPORTE_CAJERO -> TipoInstruccion.REPORTE_CAJERO;
            case TokenKind.tk_PROPINA -> TipoInstruccion.PROPINA;
            case TokenKind.tk_CODIGO_PRODUCTO -> TipoInstruccion.VENTA;
            case TokenKind.tk_ENTRADA_FONDO -> TipoInstruccion.ENTRADA_FONDO;
            case TokenKind.tk_EXTRACCION_FONDO -> TipoInstruccion.EXTRACCION_FONDO;
            case TokenKind.tk_EXTRACCION_CUP -> TipoInstruccion.EXTRACCION_CUP;
            case TokenKind.tk_ENCENDER_CAJA -> TipoInstruccion.ENCENDER_CAJA;
            case TokenKind.tk_FONDO -> TipoInstruccion.DECLARACION_EFECTIVO;
            case TokenKind.tk_CADENA_TEXTO, TokenKind.tk_LITERAL_ENTERO -> TipoInstruccion.SALVA_AUTOMATICA;
            case TokenKind.tk_TVOID, TokenKind.tk_REFUND -> TipoInstruccion.OPERACION_NEGATIVA;
            case TokenKind.tk_Cntd_X -> TipoInstruccion.FINAL_CINTA;
            case TokenKind.tk_EOT -> TipoInstruccion.EOT;
            default -> TipoInstruccion.ERROR;
        };
    }

    public CintaAuditoraElectronica parse() {
        automataHeader();
        TipoInstruccion tipoInstruccion = getTipoInstruccion();

        while (!tipoInstruccion.equals(TipoInstruccion.FINAL_CINTA) && !tipoInstruccion.equals(TipoInstruccion.EOT)) {
            tokensOperacion.clear();
            tokensOperacion.add(tokenActual);
            try {
                switch (tipoInstruccion) {
                    case TipoInstruccion.PROPINA: {
                        automataOperacionPropina();
                        break;
                    }
                    case TipoInstruccion.REPORTE_PLU: {
                        automataReportePlu();
                        break;
                    }
                    case TipoInstruccion.REPORTE_TERMINAL: {
                        automataReporteTerminal();
                        break;
                    }
                    case TipoInstruccion.REPORTE_CAJERO: {
                        automataReporteCajero();
                        break;
                    }
                    case TipoInstruccion.MANTENIMIENTO_REMOTO: {
                        automataOperacionModoProgramacion();
                        break;
                    }
                    case TipoInstruccion.VENTA: {
                        automataVenta();
                        break;
                    }
                    case TipoInstruccion.DECLARACION_EFECTIVO: {
                        automataDeclaracionEfectivo();
                        break;
                    }
                    case TipoInstruccion.ENCENDER_CAJA: {
                        automataEncenderCaja();
                        break;
                    }
                    case TipoInstruccion.SALVA_AUTOMATICA: {
                        automataSalvaAutomatica();
                        break;
                    }
                    case TipoInstruccion.ENTRADA_FONDO: {
                        automataEntradaFondo();
                        break;
                    }
                    case TipoInstruccion.EXTRACCION_FONDO: {
                        automataExtraccionFondo();
                        break;
                    }
                    case TipoInstruccion.EXTRACCION_CUP: {
                        automataExtraccionCup();
                        break;
                    }
                    case TipoInstruccion.OPERACION_NEGATIVA: {
                        automataOperacionNegativa();
                        break;
                    }
                    default: {
                        consume();
                        break;
                    }
                }
            } catch (Exception ex) {
                //poner error y skip hasta proxima operacion
            }

            //tokensOperacion = new ArrayList<>();
            consume();
            tipoInstruccion = getTipoInstruccion();
        }

        if (tipoInstruccion.equals(TipoInstruccion.FINAL_CINTA)) {
            automataFinalCinta();
        }
        cintaAuditoraElectronica.depurarDiasOperaciones();
        cintaAuditoraElectronica.depurarProductosCantidadCero();
        return cintaAuditoraElectronica;
    }

    private void automataHeader() {
        consume(3);
        cintaAuditoraElectronica.setNombrePuntoVenta(consumeAfterGetLexema());
        consume();
    }

    private void automataFinalCinta() {
        skipToTokenKind(TokenKind.Tk_FECHA_SIMPLE);
        String fecha = consumeAfterGetLexema();
        skipToTokenKind(TokenKind.tk_HORA);
        String hora = consumeAfterGetLexema();
        cintaAuditoraElectronica.setFechaHaladoVenta(fecha, hora);
        matchAndConsume(TokenKind.tk_FINAL_CINTA);
        tokenActual.match(TokenKind.tk_EOT);
    }

    private void automataOperacionModoProgramacion() {
        consume(2);
        if (matchAndConsume(TokenKind.tk_CHECKING_PLU_FILE)) {
            consume(5);
            cintaAuditoraElectronica.addOperation(new OperacionMantenimientoPLU(tokensOperacion));
        } else {
            skipToFooterToken();
            cintaAuditoraElectronica.addOperation(new OperacionMantenimiento(tokensOperacion));
        }
    }

    private void automataReportePlu() {
        skipToTokenKind(TokenKind.tk_CODIGO_REPORTE);
        TipoOperacion tipoOperacion = tokenActual.getFistCharacter().equals(CharType.X.getCharacter())
                ? TipoOperacion.X3_REPORTE_PLU
                : TipoOperacion.Z3_REPORTE_PLU;
        skipToFooterToken();
        IOperacion operation = tipoOperacion.equals(TipoOperacion.X3_REPORTE_PLU)
                ? new OperacionReportePLUX(tokensOperacion)
                : new OperacionReportePLUZ(tokensOperacion);
        cintaAuditoraElectronica.addOperation(operation);
    }

    private void automataReporteTerminal() {
        skipToTokenKind(TokenKind.tk_CODIGO_REPORTE);
        TipoOperacion tipoOperacion = tokenActual.getFistCharacter().equals(CharType.X.getCharacter())
                ? TipoOperacion.X1_REPORTE_TERMINAL
                : TipoOperacion.Z1_REPORTE_TERMINAL;
        skipToFooterToken();
        IOperacion operation = tipoOperacion.equals(TipoOperacion.X1_REPORTE_TERMINAL)
                ? new OperacionReporteTerminalX(tokensOperacion)
                : new OperacionReporteTerminalZ(tokensOperacion);
        cintaAuditoraElectronica.addOperation(operation);
    }

    private void automataReporteCajero() {
        skipToTokenKind(TokenKind.tk_CODIGO_REPORTE);
        TipoOperacion tipoOperacion = tokenActual.getFistCharacter().equals(CharType.X.getCharacter())
                ? TipoOperacion.X2_REPORTE_CAJERO
                : TipoOperacion.Z2_REPORTE_CAJERO;
        skipToFooterToken();
        IOperacion operation = tipoOperacion.equals(TipoOperacion.X2_REPORTE_CAJERO)
                ? new OperacionReporteCajeroX(tokensOperacion)
                : new OperacionReporteCajeroZ(tokensOperacion);
        cintaAuditoraElectronica.addOperation(operation);
    }

    private void automataOperacionPropina() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionPropina(tokensOperacion));
    }

    private void automataVenta() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionVenta(tokensOperacion));
    }

    private void automataOperacionNegativa() {
        TipoOperacion tipoOperacion = tokenActual.match(TokenKind.tk_REFUND)
                ? TipoOperacion.REFUND
                : TipoOperacion.TVOID;
        skipToFooterToken();
        IOperacion operation = tipoOperacion.equals(TipoOperacion.REFUND)
                ? new OperacionREFUND(tokensOperacion)
                : new OperacionTVOID(tokensOperacion);
        cintaAuditoraElectronica.addOperation(operation);
    }

    private void automataDeclaracionEfectivo() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.DECLARACION_EFECTIVO, tokensOperacion));
    }

    private void automataEncenderCaja() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.ENCENDER_CAJA, tokensOperacion));
    }

    private void automataSalvaAutomatica() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.SALVA_AUTOMATICA, tokensOperacion));
    }

    private void automataEntradaFondo() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.ENTRADA_FONDO, tokensOperacion));
    }

    private void automataExtraccionFondo() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.EXTRACCION_FONDO, tokensOperacion));
    }

    private void automataExtraccionCup() {
        skipToFooterToken();
        cintaAuditoraElectronica.addOperation(new OperacionSkiped(TipoOperacion.EXTRACCION_CUP, tokensOperacion));
    }

    private void skipToFooterToken() {
        skipToTokenKind(TokenKind.tk_FOOTER);
    }

    private void skipToTokenKind(TokenKind tokenKind) {
        try {
            while (!matchNotConsume(tokenKind)) {
                consume();
            }
        } catch (Exception e) {
            String error = e.toString();
        }
    }
}
