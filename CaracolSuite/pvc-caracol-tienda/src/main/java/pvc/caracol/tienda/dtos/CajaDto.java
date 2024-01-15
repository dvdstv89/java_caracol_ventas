package pvc.caracol.tienda.dtos;

import lombok.Data;

@Data
public class CajaDto {
    private String idCaja;
    private String codigoAlmacen;
    private Integer idCentroGestion;
    private String codigoRed;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private String modelo;
}
