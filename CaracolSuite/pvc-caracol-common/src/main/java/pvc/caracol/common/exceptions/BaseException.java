package pvc.caracol.common.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import pvc.caracol.common.reponse.WebResponse;

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
