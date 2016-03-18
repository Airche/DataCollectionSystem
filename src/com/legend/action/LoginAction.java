package com.legend.action;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.User;
import com.legend.service.RightService;
import com.legend.service.UserService;
import com.legend.util.ByteConvUtil;
import com.legend.util.DigestUtil;

@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware {
	
	private Map<String, Object> sessionMap;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="rightService")
	private RightService rightService;

	public String toLoginPage(){
		return "loginPage";
	}
	
	
	public String doLogin(){
		return SUCCESS;
	}

	public void validateDoLogin() {
		try {
			User user = userService.validateLoginInfo(model.getEmail(),ByteConvUtil.ByteToHex(DigestUtil.encode(model.getPassword())));
			if(user == null){
				this.addActionError("email/password错误");				
			}else{
				//初始化数组
				int maxPos = this.rightService.getMaxRightPos();
				user.setRightSum(new long[maxPos+1]);
				
				//计算权限总和
				user.calculateRightSum();
				this.sessionMap.put("user", user);
			}
		} catch (NoSuchAlgorithmException e) {
			this.addActionError("email/password错误");
		}
	}
	
	public String doLogout(){
		this.sessionMap.remove("user");
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

}
