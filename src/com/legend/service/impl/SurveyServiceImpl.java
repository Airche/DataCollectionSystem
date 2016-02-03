package com.legend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Page;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;
import com.legend.util.validateUtil;

@Service("surveyService")
public class SurveyServiceImpl implements SurveyService {

	@Resource(name = "surveyDao")
	private BaseDao surveyDao;

	@Resource(name = "pageDao")
	private BaseDao pageDao;

	@Override
	public List<Survey> findMySurveys(User user) {
		String hql = "FROM Survey s where s.user=?";
		List<Survey> ss = surveyDao.findEntityByHql(hql, user);
		return validateUtil.isValid(ss) ? ss : null;
	}

	@Override
	public Survey newSurvey(User user) {
		Survey survey = new Survey();
		Page page = new Page();
		survey.setUser(user);
		survey.getPages().add(page);
		survey.setCreateDate(new Date());
		page.setSurvey(survey);
		this.surveyDao.saveEntity(survey);
		this.pageDao.saveEntity(page);
		return survey;
	}

	@Override
	public List<Survey> findMySurveysForPage(User user,final int curPage,final int pageSize) {
		Survey survey = new Survey();
		survey.setTitle(null);
		survey.setUser(user);
		List<Survey> ss = surveyDao.findEntityByHqlForPage(survey, (curPage-1)*pageSize, pageSize);
		return validateUtil.isValid(ss) ? ss : null;

	}

	@Override
	public int findMySurveysPageCount(User user,int pageSize) {
		String hql = "FROM Survey s where s.user=?";
		List<Survey> ss = surveyDao.findEntityByHql(hql, user);
		int pageCount = ss.size()/pageSize;
		return ss.size()%pageSize==0?pageCount:pageCount+1;
	}

}
