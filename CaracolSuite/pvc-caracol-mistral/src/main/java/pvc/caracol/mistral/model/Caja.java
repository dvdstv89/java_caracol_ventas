package pvc.caracol.mistral.model;

import pvc.caracol.mistral.enums.ModeloCaja;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Caja {
    private String idCaja;
    private String codigoRed;
    private String codigoAlmacen;
    private String centroCosto;
    private String numeroCaja;
    private String descripcion;
    private String codigoComercial;
    private boolean mlc;
    private ModeloCaja modelo;
    private Integer idCentroGestion;
}
