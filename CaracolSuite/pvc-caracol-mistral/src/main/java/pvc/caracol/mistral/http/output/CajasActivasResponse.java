package pvc.caracol.mistral.http.output;

import pvc.caracol.mistral.dtos.CajaDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CajasActivasResponse {
   List<CajaDto> cajas;
}
