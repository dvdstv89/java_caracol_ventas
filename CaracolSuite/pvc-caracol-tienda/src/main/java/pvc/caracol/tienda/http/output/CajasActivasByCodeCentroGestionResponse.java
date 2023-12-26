package pvc.caracol.tienda.http.output;

import lombok.Data;

import java.util.List;

@Data
public class CajasActivasByCodeCentroGestionResponse {
    private String complejo;
    private String centroGestion;
    private List<?> cajasRegistradorasDto;
}
