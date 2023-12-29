package pvc.caracol.empresarial.http;

import lombok.Data;

@Data
public class DataBaseMistralDTO {
    private String host;
    private String name;
    private Integer port;
    private String username;
    private String password;
    private String description;
}
