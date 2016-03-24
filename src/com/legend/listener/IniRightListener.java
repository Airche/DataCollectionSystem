package com.legend.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.legend.model.security.Right;
import com.legend.service.RightService;

/**
 *	初始化权限监听器，在spring初始化完成后立即调用. 
 *
 */
@Component("iniRightListener")
public class IniRightListener implements ApplicationListener,ServletContextAware {

	@Resource(name="rightService")
	private RightService rightService ; 
	
	private ServletContext servletContext;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		//上下文刷新事件
		if(event instanceof ContextRefreshedEvent){
			List<Right>  rights = this.rightService.findAllEntities();
			Map<String,Right> map = new HashMap<String,Right>();
			for(Right right : rights){
				map.put(right.getRightUrl(), right);
			}
			if(this.servletContext!=null){				
				this.servletContext.setAttribute("all_rights_map", map);
				System.out.println("初始化所有权限到Application中!!!");
			}
		}
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
