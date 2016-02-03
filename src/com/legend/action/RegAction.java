package com.legend.action;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.User;
import com.legend.service.UserService;
import com.legend.util.ByteConvUtil;
import com.legend.util.DigestUtil;
import com.legend.util.validateUtil;

@Controller("regAction")
@Scope("prototype")
public class RegAction extends BaseAction<User> {

	@Resource(name = "userService")
	private UserService userService;
	
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/*
	 * 到达注册页面
	 */
	@SkipValidation
	public String toRegPage() {
		return "regPage";
	}

	/* 
	 * 完成用户注册
	 */
	public String doReg() {
		try {
			model.setPassword(ByteConvUtil.ByteToHex(DigestUtil.encode(model.getPassword())));
			model.setRegDate(new Date());
		} catch (NoSuchAlgorithmException e) {
			this.addFieldError("password", "password加密失败");
		}
		userService.saveEntity(model);
		return SUCCESS;
	}

	@Override
	public void validate() {
		// 1、非空校验
		if (!validateUtil.isValid(model.getEmail())) {
			this.addFieldError("email", "email是必填项");
		}

		if (!validateUtil.isValid(model.getPassword())) {
			this.addFieldError("password", "password是必填项");
		}

		if (!validateUtil.isValid(model.getNickName())) {
			this.addFieldError("nickName", "nickName是必填项");
		}

		if (hasErrors()) {
			return;
		}
		// 2、密码一致性
		if (!model.getPassword().equals(confirmPassword)) {
			this.addFieldError("confirmPassword", "password	不一致");
			return;
		}

		// 3、email占用
		if (userService.isRegisted(model.getEmail())) {
			this.addFieldError("email", "email已经被占用");
		}
	}

}
