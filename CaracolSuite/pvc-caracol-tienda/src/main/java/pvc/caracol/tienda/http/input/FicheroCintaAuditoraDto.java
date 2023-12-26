package pvc.caracol.tienda.http.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class FicheroCintaAuditoraDto {
    private String name;
    private Long byteSize;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate fechaCreacion;
    private Double paperSizeCentimetros;
    private Double paperSizeMetros;
    private Integer countLines;
    private String datasourse;
}
