package com.legend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TestSurveyService extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "surveyService")
	private SurveyService surveyService = null;

	@Test
	public void saveEntityTestCase() {
		User user = new User();
		user.setId(6);
		surveyService.findMySurveys(user);
	}

	@Test
	public void findMySurveysPageCountTestCase() {
		User user = new User();
		user.setId(6);
		int pageCount = surveyService.findMySurveysPageCount(user, 10);
		System.out.println(pageCount);
	}

	@Test
	public void findMySurveysForPageTestCase() {
		User user = new User();
		user.setId(6);
		// List<Survey> ss = surveyService.findMySurveysForPage(user, 1, 10);
		// System.out.println(ss);
		// List<Survey> ss1 = surveyService.findMySurveysForPage(user, 1, 10);
		// System.out.println(ss1);
		for (int i = 1; i < 10; ++i) {
			List<Survey> ss2 = surveyService.findMySurveysForPage(user, i, 10);
			System.out.println(i+"||"+ss2);
		}
	}

}
