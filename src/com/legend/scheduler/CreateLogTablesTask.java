package com.legend.scheduler;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.legend.service.LogService;
import com.legend.util.LogUtil;

/**
 *	创建石英任务 
 *
 */
public class CreateLogTablesTask extends  QuartzJobBean{

	private LogService logService;
	
	

	public void setLogService(LogService logService) {
		this.logService = logService;
	}



	/**
	 * 生成日志表
	 */
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		String tableName = LogUtil.generateLogTableName(1);
		logService.createLogTable(tableName);
		
		tableName = LogUtil.generateLogTableName(2);
		logService.createLogTable(tableName);
		
		tableName = LogUtil.generateLogTableName(3);
		logService.createLogTable(tableName);
	}

}
