package com.legend.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.dao.BaseDao;
import com.legend.model.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:beans.xml"})  
public class TestUserDao extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource(name="userDao")
	private BaseDao<User> userDao;

	@Test
	public void saveEntityTestCase() {
		User user = new User();
		user.setEmail("abc");
		userDao.saveEntity(user);
	}
	
	@Test
	public void saveOrUpdateEntityTestCase() {
		User user = new User();
		user.setId(1);
		user.setEmail("abc");
		userDao.saveOrUpdateEntity(user);
	}
	
	@Test
	public void updateEntityTestCase() {
		User user = new User();
		user.setId(1);
		user.setEmail("aaa");
		userDao.updateEntity(user);
	}
	
	@Test
	public void deleteEntityTestCase() {
		User user = new User();
		user.setId(1);
		userDao.deleteEntity(user);
	}
	
	@Test
	public void batchEntityBySqlTestCase() {
		userDao.batchEntityBySql("update users u set email=? where id=?","xxx","2");
	}
	
	@Test
	public void loadEntityTestCase() {
		User user = userDao.loadEntity(User.class, 2);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void getEntityTestCase() {
		User user = userDao.getEntity(User.class, 2);
		Assert.assertNotNull(user);
	}
	
	@Test
	public void findEntityByHqlTestCase() {
		List<User> users = userDao.findEntityByHql("FROM User u where email like ?", "%Z%");
		Assert.assertTrue(users.size()>0);
	}
}
