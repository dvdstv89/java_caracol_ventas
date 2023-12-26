package pvc.caracol.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pvc.caracol.common.reponse.ApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BaseRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus()
    public ResponseEntity<ApiResponse> handleException(Exception exception) {
        Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ApiResponse>>> exceptionHandlers = new HashMap<>();
        exceptionHandlers.put(NotFoundException.class, this::localNoFoundException);
        exceptionHandlers.put(BadRequestException.class, this::localBadRequestException);
        exceptionHandlers.put(FeignClientException.class, this::localFeignException);
        return exceptionHandlers.getOrDefault(exception.getClass(), null).apply(exception);
    }

    public ResponseEntity<ApiResponse> localNoFoundException(Exception ex) {
        NotFoundException exception = (NotFoundException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }

    public ResponseEntity<ApiResponse> localBadRequestException(Exception ex) {
        BadRequestException exception = (BadRequestException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }

    public ResponseEntity<ApiResponse> localFeignException(Exception ex) {
        FeignClientException exception = (FeignClientException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }
}
