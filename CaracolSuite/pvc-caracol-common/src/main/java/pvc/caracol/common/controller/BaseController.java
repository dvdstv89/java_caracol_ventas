package pvc.caracol.common.controller;

import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import pvc.caracol.common.messages.MessageText;
import pvc.caracol.common.reponse.ApiWebResponse;

import java.util.List;

public abstract class BaseController {
    private final Logger logger;

    public BaseController(Logger logger) {
        this.logger = logger;
    }

    protected <T> ResponseEntity<T> handleApiResponseToObject(ApiWebResponse response, String endpoint) {
        handleResponseLogger(response, endpoint);
        return new ResponseEntity<>((T) response.getResult(), response.getStatusCode());
    }

    protected <T> ResponseEntity<List<T>> handleApiResponseToObjectList(ApiWebResponse response, String endpoint) {
        handleResponseLogger(response, endpoint);
        return new ResponseEntity<>((List<T>) response.getResult(), response.getStatusCode());
    }

    protected ResponseEntity<ApiWebResponse> handleApiResponse(ApiWebResponse response, String endpoint) {
        handleResponseLogger(response, endpoint);
        return new ResponseEntity<ApiWebResponse>(response, response.getStatusCode());
    }

    private void handleResponseLogger(ApiWebResponse response, String endpoint) {
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
