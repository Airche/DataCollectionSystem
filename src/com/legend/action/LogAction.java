package com.legend.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.Log;
import com.legend.service.LogService;

@Controller("logAction")
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private List<Log> allLogs;
	
	@Resource(name="logService")
	private LogService logService;
	
	public String findAllLogs() {
		this.allLogs = this.logService.findAllEntities();
		return "logListPage";
	}

	public String findNearestLogs(){
		this.allLogs = this.logService.findNearestLogs(-1);
		return "logListPage";
	}
	
	public List<Log> getAllLogs() {
		return allLogs;
	}

	public void setAllLogs(List<Log> allLogs) {
		this.allLogs = allLogs;
	}
	
	
}
