package com.legend.action.intercepor;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import com.legend.service.RightService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CatchUrlInterceptor implements Interceptor {

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy actionProxy  = invocation.getProxy();
		String ns = actionProxy.getNamespace();
		String actionName = actionProxy.getActionName();
		String method = actionProxy.getMethod();
		if(ns==null||ns.equals("/")){
			ns="";
		}
		String url = ns + "/" +actionName;
		ActionContext  actionContext  = invocation.getInvocationContext();
		ApplicationContext ac = (ApplicationContext) actionContext.getApplication().get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RightService rs = (RightService) ac.getBean("rightService");
		rs.appendRightByURL(url);
		return invocation.invoke();
	}

}
