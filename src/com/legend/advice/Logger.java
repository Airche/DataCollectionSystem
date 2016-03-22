package com.legend.advice;

import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;

import com.legend.model.Log;
import com.legend.model.User;
import com.legend.service.LogService;
import com.opensymphony.xwork2.ActionContext;

public class Logger {
	
	@Resource(name="logService")
	private LogService logService;


	public Object record(ProceedingJoinPoint pjp)  {
		Log log = new Log();
		try{
			ActionContext ac = ActionContext.getContext();
			if(ac!=null){
				Map<String, Object> session = ac.getSession();
				if(session!=null){
					User user = (User) session.get("user");
					if(user!=null){
						log.setOperator(user.getId()+":"+user.getEmail());
					}
				}
			}
			
			String name = pjp.getSignature().getName();
			log.setOperName(name);
			
			Object[] objects = pjp.getArgs();
			String params = "";
			for(Object object : objects){
				params = params + object.toString() + ",";
			}
			log.setOperParams(params);
			
			//调用目标对象方法
			Object ret = pjp.proceed();
			
			log.setOperResult("success");
			
			if(ret!=null){
				log.setResultMsg(ret.toString());	
			}
			
			return ret;
		}catch (Throwable e){
			log.setOperResult("failure");
			log.setResultMsg(e.getMessage());
		}finally{
			logService.saveEntity(log);
		}
		
		return null;
	}

}
