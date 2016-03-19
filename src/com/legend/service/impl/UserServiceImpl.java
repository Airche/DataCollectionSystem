package com.legend.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.User;
import com.legend.model.security.Right;
import com.legend.model.security.Role;
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
		for(User u : us){
			for(Role r : u.getRoles()){
				r.getRights().size();
			}
		}
		return validateUtil.isValid(us)?us.get(0):null;
	}

	@Override
	public User getEntityWithChild(Integer userId) {
		User user = this.getEntity(User.class, userId);
		user.getRoles().size();
		return user;
	}

	@Override
	public void saveOrUpdateAuthorize(Integer userId, Integer[] ownRolesIds) {
		Set<Role> roles = new HashSet<Role>();
		if(ownRolesIds != null){
			for(Integer ownRolesId : ownRolesIds){
				Role role = new Role();
				role.setId(ownRolesId);
				roles.add(role);
			}
		}
		User user = this.getEntity(User.class, userId);
		user.setRoles(roles);
	}

	@Override
	public void clearAuthorize(Integer userId) {
		this.getEntity(User.class, userId).getRoles().clear();
	}

}
