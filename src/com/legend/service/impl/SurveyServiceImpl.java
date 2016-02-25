package com.legend.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.legend.dao.BaseDao;
import com.legend.model.Page;
import com.legend.model.Question;
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

	@Resource(name = "questionDao")
	private BaseDao questionDao;

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
		/***********/
		Question question = new Question();
		question.setTitle("question title");
		question.setQuestionType(1);
		question.setOptions("12-23-34");
		page.getQuestions().add(question);
		question.setPage(page);
		this.questionDao.saveEntity(question);
		/**********/
		survey.setUser(user);
		survey.getPages().add(page);
		survey.setCreateDate(new Date());
		page.setSurvey(survey);
		this.surveyDao.saveEntity(survey);
		this.pageDao.saveEntity(page);
		return survey;
	}

	@Override
	public List<Survey> findMySurveysForPage(User user, final int curPage, final int pageSize) {
		Survey survey = new Survey();
		survey.setTitle(null);
		survey.setUser(user);
		List<Survey> ss = surveyDao.findEntityByHqlForPage(survey, (curPage - 1) * pageSize, pageSize);
		return validateUtil.isValid(ss) ? ss : null;

	}

	@Override
	public int findMySurveysPageCount(User user, int pageSize) {
		String hql = "FROM Survey s where s.user=?";
		List<Survey> ss = surveyDao.findEntityByHql(hql, user);
		int pageCount = ss.size() / pageSize;
		return ss.size() % pageSize == 0 ? pageCount : pageCount + 1;
	}

	@Override
	public Survey getSurvey(int surveyId) {
		return (Survey) this.surveyDao.getEntity(Survey.class, surveyId);
	}

	@Override
	public Survey getSurveyWithChildren(int surveyId) {
		Survey s = this.getSurvey(surveyId);
		//强制加载
		if (s!=null&&s.getPages() != null) {
			for (Page p : s.getPages())
				p.getQuestions().size();
		} 
		return s;
	}

	@Override
	public void update(Survey model) {
		this.surveyDao.saveOrUpdateEntity(model);
	}
	
	@Override
	public void saveOrUpdatePage(Page model) {
		this.pageDao.saveOrUpdateEntity(model);
	}

	@Override
	public Page getPageById(int pageId) {
		return (Page) this.pageDao.getEntity(Page.class, pageId);
	}

	@Override
	public void saveOrUpdateQuestion(int pageId, Question model) {
		Page page = (Page) this.pageDao.getEntity(Page.class, pageId);
		page.getQuestions().add(model);
		model.setPage(page);
		this.questionDao.saveOrUpdateEntity(model);	
	}

}
