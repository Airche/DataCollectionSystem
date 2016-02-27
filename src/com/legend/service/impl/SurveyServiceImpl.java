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
	
	@Resource(name = "answerDao")
	private BaseDao answerDao;

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
		/***********
		Question question = new Question();
		question.setTitle("question title");
		question.setQuestionType(1);
		question.setOptions("12-23-34");
		page.getQuestions().add(question);
		question.setPage(page);
		this.questionDao.saveEntity(question);
		**********/
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
	public void saveOrUpdateQuestion(Question model) {
		this.questionDao.saveOrUpdateEntity(model);	
	}

	@Override
	public void deleteQuestion(int questionId) {
		String sql = "delete from ANSWERS where questionId=?";
		this.answerDao.batchEntityBySql(sql, questionId);
		sql = "delete from QUESTIONS where id=?";
		this.questionDao.batchEntityBySql(sql, questionId);
	}

	@Override
	public void deletePage(int pageId) {
		String sql = "delete from ANSWERS where questionId in (select id from QUESTIONS where pageid=?)";
		this.answerDao.batchEntityBySql(sql, pageId);
		sql = "delete from QUESTIONS where pageid=?";
		this.questionDao.batchEntityBySql(sql, pageId);
		sql = "delete from PAGES where id=?";
		this.pageDao.batchEntityBySql(sql, pageId);
	}

	/*
	 * 不用级联删除，因为效率低
	 */
	@Override
	public void deleteSurvey(int surveyId) {
		String sql = "delete from ANSWERS where surveyid=?";
		this.answerDao.batchEntityBySql(sql, surveyId);
		sql = "delete from QUESTIONS where pageid in (select id from PAGES where surveyid=?)";
		this.questionDao.batchEntityBySql(sql, surveyId);
		sql = "delete from PAGES where surveyid=?";
		this.pageDao.batchEntityBySql(sql, surveyId);
		sql= "delete from SURVEYS where id=?";
		this.surveyDao.batchEntityBySql(sql, surveyId);
	}

	@Override
	public Question getQuestion(Integer id) {
		return (Question) this.questionDao.getEntity(Question.class, id);
	}

	@Override
	public void clearAnswer(int surveyId) {
		String sql = "delete from ANSWERS where surveyid=?";
		this.answerDao.batchEntityBySql(sql, surveyId);
	}

	@Override
	public void toogleStatus(int surveyId) {
		String sql = "update SURVEYS set closed = !closed where id=?";
		this.surveyDao.batchEntityBySql(sql, surveyId);
	}

	@Override
	public void addLogoPath(int surveyId, String logoPhotoPath) {
		String sql = "update SURVEYS set logoPhotoPath=? where id=?";
		this.surveyDao.batchEntityBySql(sql, logoPhotoPath,surveyId);
	}


}
