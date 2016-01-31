package com.legend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.User;
import com.legend.service.UserService;
import com.legend.util.validateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{

	@Override
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public boolean isRegisted(String email) {
		String hql = "FROM User u where u.email=?";
		List<User> us = this.findEntityByHql(hql, email);
		return validateUtil.isValid(us);
	}

	@Override
	public User validateLoginInfo(String email, String md5) {
		String hql = "FROM User u where u.email=? and u.password=?";
		List<User> us = this.findEntityByHql(hql, email,md5);
		return validateUtil.isValid(us)?us.get(0):null;
	}

}
