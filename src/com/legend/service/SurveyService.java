package com.legend.service;

import java.util.List;

import com.legend.model.Page;
import com.legend.model.Question;
import com.legend.model.Survey;
import com.legend.model.User;

public interface SurveyService {

	List<Survey> findMySurveys(User user);

	Survey newSurvey(User user);
	
	List<Survey> findMySurveysForPage(User user,final int curPage,final int pageSize);

	int findMySurveysPageCount(User user,int pageSize);

	Survey getSurvey(int surveyId);

	Survey getSurveyWithChildren(int surveyId);

	void update(Survey model);
	
	void saveOrUpdatePage(Page model);

	Page getPageById(int pageId);

	void saveOrUpdateQuestion(Question model);

	void deleteQuestion(int questionId);

	void deletePage(int pageId);

	void deleteSurvey(int surveyId);

	Question getQuestion(Integer id);

	void clearAnswer(int surveyId);

	void toogleStatus(int surveyId);

}
