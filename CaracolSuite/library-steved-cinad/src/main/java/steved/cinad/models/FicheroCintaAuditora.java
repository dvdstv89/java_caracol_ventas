package steved.cinad.models;

import lombok.Builder;
import lombok.Data;

import java.nio.file.attribute.FileTime;
import java.util.Date;

@Data
@Builder
public class FicheroCintaAuditora {
    private String name;
    private Long byteSize;
    private Date fechaCreacion;
    private Double paperSizeCentimetros;
    private Double paperSizeMetros;
    private Integer countLines;
    private String datasourse;
    public void setCountLines(String sourse){
        this.countLines = sourse.split("\n").length;
    }

    public void setPaperSizeCentimetros(String sourse){
        this.paperSizeCentimetros = sourse.split("\n").length / 3.6;
    }

    public void setPaperSizeMetros(String sourse){
        this.paperSizeMetros = sourse.split("\n").length / 360.0;
    }
}
