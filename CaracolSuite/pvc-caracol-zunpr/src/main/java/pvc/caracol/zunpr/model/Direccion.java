package pvc.caracol.zunpr.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Direccion {
    private String direccion;
    private String municipio;
    private String provincia;
}