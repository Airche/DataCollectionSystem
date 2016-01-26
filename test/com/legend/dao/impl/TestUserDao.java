package com.legend.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.legend.dao.BaseDao;
import com.legend.model.User;

@SuppressWarnings("unchecked")
public class TestUserDao {

	@Test
	public void saveEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = new User();
		user.setEmail("abc");
		userDao.saveEntity(user);
	}
	
	@Test
	public void saveOrUpdateEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = new User();
		user.setId(1);
		user.setEmail("abc");
		userDao.saveOrUpdateEntity(user);
	}
	
	@Test
	public void updateEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = new User();
		user.setId(1);
		user.setEmail("aaa");
		userDao.updateEntity(user);
	}
	
	@Test
	public void deleteEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = new User();
		user.setId(1);
		userDao.deleteEntity(user);
	}
	
	@Test
	public void batchEntityBySqlTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		userDao.batchEntityBySql("update users u set email=? where id=?","xxx","2");
	}
	
	@Test
	public void loadEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = (User) userDao.loadEntity(User.class, 2);
		System.out.println(user);
	}
	
	@Test
	public void getEntityTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		User user = (User) userDao.getEntity(User.class, 2);
		System.out.println(user.getEmail());
	}
	
	@Test
	public void findEntityByHqlTestCase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		BaseDao userDao = (BaseDao)ctx.getBean("userDao");
		List<User> users = userDao.findEntityByHql("FROM User u where email like ?", "%x%");
		System.out.println(users.get(0).getEmail());
	}
}
