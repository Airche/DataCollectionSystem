package com.legend.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements SessionAware {
	
	private Map<String, Object> sessionMap;
	private List<Survey> mySurveys;
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	
	public String mySurveys(){
		User user = (User) this.sessionMap.get("user");
		this.mySurveys = this.surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}
	
	
}
