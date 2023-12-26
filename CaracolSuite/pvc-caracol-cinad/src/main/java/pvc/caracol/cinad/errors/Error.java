package pvc.caracol.cinad.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private Integer linea;
    private String texto;
    private TipoErrorCompilacion tipoError;
}
