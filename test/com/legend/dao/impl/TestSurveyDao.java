package com.legend.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.dao.BaseDao;
import com.legend.model.Survey;
import com.legend.model.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:beans.xml"})  
public class TestSurveyDao extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Resource(name="surveyDao")
	private BaseDao<Survey> surveyDao;

	@Test
	public void saveEntityTestCase() {
		Survey survey = new Survey();
		survey.setTitle("111");
		User user = new User();
		user.setId(6);
		survey.setUser(user);
		survey.setCreateDate(new Date());
		surveyDao.saveEntity(survey);
	}
	
	@Test
	public void findEntityByHqlForPageTestCase(){
		Survey survey = new Survey();
		survey.setTitle(null);
		User user = new User();
		user.setId(6);
		survey.setUser(user);
		List<Survey> ss = surveyDao.findEntityByHqlForPage(survey,0,100);
		for(Survey s:ss){
			System.out.println(s.getId());
		}
	}
	
}
