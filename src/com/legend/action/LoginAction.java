package com.legend.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.User;
import com.legend.service.UserService;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> {
	
	@Resource(name="userService")
	private UserService userService;

	
	public String toLoginPage(){
		return "loginPage";
	}
	
	public String doLogin(){
		return SUCCESS;
	}

}
