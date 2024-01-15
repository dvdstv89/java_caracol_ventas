package pvc.caracol.common.service;

import pvc.caracol.common.reponse.ApiWebResponse;

import java.util.List;

public abstract class BaseService {
    protected ApiWebResponse response;

    public BaseService()
    {
        response = new ApiWebResponse();
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
