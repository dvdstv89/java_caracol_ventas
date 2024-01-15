package pvc.caracol.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import pvc.caracol.common.reponse.ApiWebResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends Exception {
    protected ApiWebResponse apiResponse;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        apiResponse = new ApiWebResponse();
        apiResponse.getErrors().add(message);
        apiResponse.setStatusCode(httpStatus);
    }
}
