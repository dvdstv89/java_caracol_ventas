package pvc.caracol.common.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import pvc.caracol.common.reponse.ApiResponse;

public class FeignClientException extends BaseException {
    public FeignClientException(FeignException feignException) {
        super("", HttpStatus.MULTI_STATUS);
        this.apiResponse = obtenerApiResponse(feignException);
    }

    private ApiResponse obtenerApiResponse(FeignException feignException) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(feignException.contentUTF8(), ApiResponse.class);
        } catch (Exception exception) {
            return null;
        }
    }
}