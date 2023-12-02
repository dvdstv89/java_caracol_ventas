package com.system.mistral.service;

import com.system.mistral.model.DataBaseInfo;

public interface IDataBaseInfoService {
    DataBaseInfo getDatabaseInfo(String centroGestion);
}
