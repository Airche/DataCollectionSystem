package com.legend.service;

import com.legend.model.Log;

public interface LogService extends BaseService<Log> {

	void createLogTable(String tableName);

}
