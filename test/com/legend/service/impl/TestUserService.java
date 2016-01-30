package com.legend.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.model.User;
import com.legend.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:beans.xml"})  
public class TestUserService extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource(name="userService")
	private UserService	userService = null;
	
	@Test
	public void saveEntityTestCase(){
		User user = new User();
		user.setEmail("YYY");
		userService.saveEntity(user);
	}
}
