package com.legend.action.intercepor;

import com.legend.action.BaseAction;
import com.legend.action.LoginAction;
import com.legend.action.RegAction;
import com.legend.model.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		BaseAction baseAction = (BaseAction) invocation.getAction();
		if(baseAction instanceof LoginAction || baseAction instanceof RegAction){
			return invocation.invoke();
		}
		User user = (User) invocation.getInvocationContext().getSession().get("user");
		if(user==null){
			return "login";
		}else{
			if(baseAction instanceof UserAware){
				((UserAware) baseAction).setUser(user);
			}
			return invocation.invoke();
		}
	}

}
