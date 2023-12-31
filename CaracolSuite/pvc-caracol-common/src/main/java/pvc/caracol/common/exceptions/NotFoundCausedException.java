package pvc.caracol.common.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundCausedException extends BaseException {
    public NotFoundCausedException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
}
