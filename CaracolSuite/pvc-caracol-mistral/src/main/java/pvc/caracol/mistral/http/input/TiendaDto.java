package pvc.caracol.mistral.http.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TiendaDto {
    private String codeTienda;
    private Integer idCentroGestion;
}
