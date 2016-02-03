package com.legend.service;

import java.util.List;

import com.legend.model.Survey;
import com.legend.model.User;

public interface SurveyService {

	List<Survey> findMySurveys(User user);

	Survey newSurvey(User user);
	
	List<Survey> findMySurveysForPage(User user,final int curPage,final int pageSize);

	int findMySurveysPageCount(User user,int pageSize);

}
