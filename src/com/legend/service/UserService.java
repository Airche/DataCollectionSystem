package com.legend.service;

import com.legend.model.User;

public interface UserService extends BaseService<User> {

	/*
	 * 判断email是否被占用
	 */
	boolean isRegisted(String email);

}
