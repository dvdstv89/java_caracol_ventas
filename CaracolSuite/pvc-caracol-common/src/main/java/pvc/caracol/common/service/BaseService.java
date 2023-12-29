package pvc.caracol.common.service;

import pvc.caracol.common.reponse.WebResponse;

import java.util.List;

public abstract class BaseService {
    protected WebResponse response;

    public BaseService()
    {
        response = new WebResponse();
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
