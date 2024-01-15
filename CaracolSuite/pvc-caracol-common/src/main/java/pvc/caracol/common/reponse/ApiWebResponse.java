package pvc.caracol.common.reponse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@JsonPropertyOrder({"statusCode", "isValid", "date", "result", "errors"})
public class ApiWebResponse {
    private HttpStatus statusCode;
    @JsonProperty("isValid")
    private boolean isValid;
    private List<String> errors;
    private Object result;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Havana")
    private Date date;

    public ApiWebResponse() {
        errors = new ArrayList<>();
        date = new Date();
    }

    @JsonIgnore
    public List<LinkedHashMap<String, String>> getResultAsList(){
       return  (List<LinkedHashMap<String, String>>) result;
    }

    @JsonIgnore
    public LinkedHashMap<String, Object> getResultAsMap(){
        return  (LinkedHashMap<String, Object>) result;
    }

    public void addOkResponse200(Object result) {
        setResult(result);
        setStatusCode(HttpStatus.OK);
        setValid(true);
    }

    public void addCreateResponse201(Object result) {
        setResult(result);
        setStatusCode(HttpStatus.CREATED);
        setValid(true);
    }
}
