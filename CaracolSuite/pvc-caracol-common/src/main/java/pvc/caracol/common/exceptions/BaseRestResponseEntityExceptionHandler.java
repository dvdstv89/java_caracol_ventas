package pvc.caracol.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pvc.caracol.common.reponse.ApiWebResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class BaseRestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus()
    public ResponseEntity<ApiWebResponse> handleException(Exception exception) {
        Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ApiWebResponse>>> exceptionHandlers = new HashMap<>();
        exceptionHandlers.put(NotFoundException.class, this::localNoFoundException);
        exceptionHandlers.put(NotFoundCausedException.class, this::localNoFoundCausedException);
        exceptionHandlers.put(BadRequestException.class, this::localBadRequestException);
        exceptionHandlers.put(FeignClientException.class, this::localFeignException);
        return exceptionHandlers.getOrDefault(exception.getClass(), null).apply(exception);
    }

    public ResponseEntity<ApiWebResponse> localNoFoundException(Exception ex) {
        NotFoundException exception = (NotFoundException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }

    public ResponseEntity<ApiWebResponse> localNoFoundCausedException(Exception ex) {
        NotFoundCausedException exception = (NotFoundCausedException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }

    public ResponseEntity<ApiWebResponse> localBadRequestException(Exception ex) {
        BadRequestException exception = (BadRequestException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }

    public ResponseEntity<ApiWebResponse> localFeignException(Exception ex) {
        FeignClientException exception = (FeignClientException) ex;
        return new ResponseEntity<>(exception.getApiResponse(), exception.getApiResponse().getStatusCode());
    }
}
