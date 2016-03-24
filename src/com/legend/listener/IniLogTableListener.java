package com.legend.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.legend.service.LogService;
import com.legend.uitl.LogUtil;

/**
 * 初始化权限监听器，在spring初始化完成后立即调用.
 *
 */
@Component("iniLogTableListener")
public class IniLogTableListener implements ApplicationListener {

	@Resource(name = "logService")
	private LogService logService;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		
		if (event instanceof ContextRefreshedEvent) {
			String tableName = LogUtil.generateLogTableName(0);
			logService.createLogTable(tableName);

			tableName = LogUtil.generateLogTableName(1);
			logService.createLogTable(tableName);

			tableName = LogUtil.generateLogTableName(2);
			logService.createLogTable(tableName);

			System.out.println("初始化日志表完成!!!");
		}
		
	}

}
