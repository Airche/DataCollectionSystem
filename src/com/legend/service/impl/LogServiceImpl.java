package com.legend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Log;
import com.legend.service.LogService;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Override
	@Resource(name = "logDao")
	public void setBaseDao(BaseDao<Log> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public void createLogTable(String tableName) {
		String sql = "create table if not exists "+tableName + " like logs";
		this.batchEntityBySql(sql);
	}
}
