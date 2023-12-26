package pvc.caracol.common.service;

import pvc.caracol.common.reponse.ApiResponse;

import java.util.List;

public abstract class BaseService {
    protected ApiResponse response;

    public BaseService()
    {
        response = new ApiResponse();
    }

    protected boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        }

        if (object instanceof String) {
            return ((String) object).isEmpty();
        }

        if (object instanceof List) {
            return ((List<?>) object).isEmpty();
        }
        return false;
    }
}
