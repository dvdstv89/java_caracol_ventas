package pvc.caracol.cinad.models;

import lombok.Builder;
import lombok.Data;
import pvc.caracol.cinad.analizador.lexico.tokens.SourceStream;

import java.time.LocalDateTime;

@Data
@Builder
public class FicheroCintaAuditora {
    private String name;
    private Long byteSize;
    private LocalDateTime fechaCreacion;
    private Double paperSizeCentimetros;
    private Double paperSizeMetros;
    private Integer countLines;
    private String datasourse;
    private SourceStream sourceStream;

    public void setCountLines(String sourse) {
        this.countLines = sourse.split("\n").length;
    }

    public void setPaperSizeCentimetros(String sourse) {
        this.paperSizeCentimetros = sourse.split("\n").length / 3.6;
    }

    public void setPaperSizeMetros(String sourse) {
        this.paperSizeMetros = sourse.split("\n").length / 360.0;
    }

    public void setSourceStream(String sourse) {
        this.sourceStream = new SourceStream(sourse);
    }
}
