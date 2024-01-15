package pvc.caracol.empresarial.http.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TiendaDto {
    String codeTienda;
    Integer idCentroGestion;
}
