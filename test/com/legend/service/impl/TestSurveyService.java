package com.legend.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.model.User;
import com.legend.service.SurveyService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:beans.xml"})  
public class TestSurveyService extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource(name="surveyService")
	private SurveyService	surveyService = null;
	
	@Test
	public void saveEntityTestCase(){
		User user = new User();
		user.setId(6);
		surveyService.findMySurveys(user);
	}
}
