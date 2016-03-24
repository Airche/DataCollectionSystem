package com.legend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Log;
import com.legend.service.LogService;
import com.legend.uitl.LogUtil;

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

	@Override
	public void saveEntity(Log t) {
		String sql = "insert into "
							+LogUtil.generateLogTableName(0)
							+"(operator,operName,operParams,operResult,resultMsg,operTime) values(?,?,?,?,?,?)";
	    this.batchEntityBySql(sql, t.getOperator(),t.getOperName(),t.getOperParams(),t.getOperResult(),t.getResultMsg(),t.getOperTime());
	}

	@Override
	public List<Log> findNearestLogs(int n) {
		List<Log> logs = new ArrayList<Log>();
		for(int i=0;i>=n;--i){
			String sql = "select * From " + LogUtil.generateLogTableName(i) + " order by id Desc";
			List<Log> log = this.executeSqlQuery(Log.class,sql);
			logs.addAll(log);
		}
		return logs;
	}
	
}
