package pvc.caracol.tienda.dtos;

import lombok.Data;

@Data
public class CajaDto {
    private String idCaja;
    private String codigoRed;
    private String codigoCentroGestion;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private String modelo;
}
