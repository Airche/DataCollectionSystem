package com.legend.service;

import java.util.List;

import com.legend.model.Survey;
import com.legend.model.User;

public interface SurveyService {

	List<Survey> findMySurveys(User user);

}
