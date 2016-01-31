package com.legend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;
import com.legend.util.validateUtil;

@Service("surveyService")
public class SurveyServiceImpl  implements SurveyService {
	
	@Resource(name="surveyDao")
	private BaseDao surveyDao;
	

	@Override
	public List<Survey> findMySurveys(User user) {
		String hql = "FROM Survey s where s.user=?";
		List<Survey> ss = surveyDao.findEntityByHql(hql, user);
		return validateUtil.isValid(ss)?ss:null;
	}

}
