package com.legend.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware{
	
	private Map<String, Object> sessionMap;
	private List<Survey> mySurveys;
	private int curPage;
	private int pageSize;
	private int pageCount;
	private int nextForward ;
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	
	private User user;
	
	public String mySurveys(){
		this.mySurveys = this.surveyService.findMySurveys(user);
		return "mySurveyListPage";
	}

	public String mySurveysForPage(){
		this.pageCount = this.surveyService.findMySurveysPageCount(user,pageSize);
		if(nextForward==0){			
			this.mySurveys = this.surveyService.findMySurveysForPage(user,curPage,pageSize);
		}else if(nextForward>0){
			curPage += 1;
			this.mySurveys = this.surveyService.findMySurveysForPage(user,curPage,pageSize);
		}else if(nextForward<0){
			curPage -= 1;
			this.mySurveys = this.surveyService.findMySurveysForPage(user,curPage,pageSize);
		}
		return "mySurveyListPageForPage";
	}
	
	public String newSurvey(){
		this.model = this.surveyService.newSurvey(user);
		return "designSurveysPage";
	}

	public List<Survey> getMySurveys() {
		return mySurveys;
	}

	public void setMySurveys(List<Survey> mySurveys) {
		this.mySurveys = mySurveys;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getNextForward() {
		return nextForward;
	}

	public void setNextForward(int nextForward) {
		this.nextForward = nextForward;
	}

	
}
