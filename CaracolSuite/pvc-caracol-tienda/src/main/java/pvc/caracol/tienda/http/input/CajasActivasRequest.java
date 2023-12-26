package pvc.caracol.tienda.http.input;

import lombok.Data;

import java.util.List;

@Data
public class CajasActivasRequest {
    List<CajaDto> cajas;
}
