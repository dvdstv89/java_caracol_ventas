package pvc.caracol.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import pvc.caracol.common.reponse.WebResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends Exception {
    protected WebResponse apiResponse;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        apiResponse = new WebResponse();
        apiResponse.getErrors().add(message);
        apiResponse.setStatusCode(httpStatus);
    }
}
