package pvc.caracol.common.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import pvc.caracol.common.messages.MessageText;
import pvc.caracol.common.reponse.WebResponse;

import java.util.List;

public abstract class BaseController {
    private final Logger logger;

    public BaseController(Logger logger) {
        this.logger = logger;
    }

    protected <T> ResponseEntity<T> handleApiResponseToObject(WebResponse response, String endpoint) {
        handleResponseLogger(response, endpoint);
        return new ResponseEntity<>((T) response.getResult(), response.getStatusCode());
    }

    protected <T> ResponseEntity<List<T>> handleApiResponseToObjectList(WebResponse response, String endpoint) {
        handleResponseLogger(response, endpoint);
        return new ResponseEntity<>((List<T>) response.getResult(), response.getStatusCode());
    }

    private void handleResponseLogger(WebResponse response, String endpoint) {
        switch (response.getStatusCode()) {
            case CREATED:
                logInfo(String.format(MessageText.HANDLE_API_RESPONSE_CREATED, endpoint));
                break;
            default:
                logInfo(String.format(MessageText.HANDLE_API_RESPONSE_OK, endpoint));
                break;
        }
    }

    private void logInfo(String message) {
        if (logger != null) {
            logger.info(message);
        }
    }
}
