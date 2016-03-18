package com.legend.action.intercepor;

import org.apache.struts2.ServletActionContext;

import com.legend.action.BaseAction;
import com.legend.action.LoginAction;
import com.legend.action.RegAction;
import com.legend.model.User;
import com.legend.util.validateUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class RightFilterInterceptor implements Interceptor {

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@SuppressWarnings("unused")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		ActionProxy actionProxy = invocation.getProxy();
		String ns = actionProxy.getNamespace();
		String actionName = actionProxy.getActionName();
		if(baseAction instanceof LoginAction || baseAction instanceof RegAction){
			return invocation.invoke();
		}
		if(validateUtil.hasRight(ns, actionName, ServletActionContext.getRequest())){
			User user = (User) invocation.getInvocationContext().getSession().get("user");
			if(baseAction instanceof UserAware){
				((UserAware) baseAction).setUser(user);
			}
			return invocation.invoke();
		}else{
			return "login";
		}
	}

}
