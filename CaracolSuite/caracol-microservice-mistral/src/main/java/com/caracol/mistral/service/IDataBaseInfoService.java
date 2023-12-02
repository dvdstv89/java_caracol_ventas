package com.caracol.mistral.service;

import com.caracol.mistral.model.DataBaseInfo;

public interface IDataBaseInfoService {
    DataBaseInfo getDatabaseInfo(String centroGestion);
}
