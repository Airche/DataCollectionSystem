package com.legend.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Page;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("engageSurveyAction")
@Scope("prototype")
public class EngageSurveyAction extends BaseAction<Survey> implements UserAware,SessionAware {

	private User user;
	private List<Survey> surveys;
	private int surveyId;
	private Page curPage;
	private Map<String, Object>  sessionMap;
	private int pageCount;
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	
	public String toAvailableSurveyPage() {
		this.surveys = this.surveyService.findMyAvailableSurveys(user);
		return "availableSurveyPage";
	}
	
	public String entry(){
		Survey survey = this.surveyService.getSurvey(this.surveyId);
		sessionMap.put("CurSurvey", survey);
		this.pageCount = this.surveyService.getSurveyPageCount(this.surveyId);
		this.curPage = this.surveyService.getFirstPage(this.surveyId);
		return "engageSurveyPage";
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public Page getCurPage() {
		return curPage;
	}

	public void setCurPage(Page curPage) {
		this.curPage = curPage;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.sessionMap = session;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	
}
