package pvc.caracol.common.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import pvc.caracol.common.reponse.ApiResponse;

@Data
public abstract class BaseException extends Exception {
    protected ApiResponse apiResponse;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        apiResponse = new ApiResponse();
        apiResponse.getErrors().add(message);
        apiResponse.setStatusCode(httpStatus);
    }
}
