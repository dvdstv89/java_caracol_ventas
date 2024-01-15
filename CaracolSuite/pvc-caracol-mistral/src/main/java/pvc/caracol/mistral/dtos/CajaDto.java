package pvc.caracol.mistral.dtos;

import lombok.Builder;
import lombok.Data;

@Data
public class CajaDto {
    private String idCaja;
    private String codigoAlmacen;
    private String codigoRed;
    private String centroCosto;
    private String numeroCaja;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private String modelo;
    private Integer idCentroGestion;
}
