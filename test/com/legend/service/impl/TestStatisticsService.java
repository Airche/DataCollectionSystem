package com.legend.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.legend.model.statistics.QuestionStatisticsModel;
import com.legend.service.StatisticsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:beans.xml" })
public class TestStatisticsService {
	
	@Resource(name = "statisticsService")
	private StatisticsService statisticsService;

	
	@Test
	public void statisticsTestCase(){
		QuestionStatisticsModel  qsm = statisticsService.statistics(50);
		System.out.println(qsm);
	}
}
