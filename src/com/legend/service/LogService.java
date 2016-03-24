package com.legend.service;

import java.util.List;

import com.legend.model.Log;

public interface LogService extends BaseService<Log> {

	void createLogTable(String tableName);

	List<Log> findNearestLogs(int i);

}
