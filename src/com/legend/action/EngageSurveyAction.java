package com.legend.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ParameterAware;
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
public class EngageSurveyAction extends BaseAction<Survey> implements UserAware,SessionAware,ParameterAware{

	private User user;
	private List<Survey> surveys;
	private int surveyId;
	private Page curPage;
	private int orderNo;
	private Map<String, Object>  sessionMap;
	private int pageCount;
	private Map<String, String[]> parameters;
	
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
		sessionMap.put("AnswerMap", new HashMap<Integer,Map<String,String[]>>());
		return "engageSurveyPage";
	}

	public String doEngageSurvey(){
		Map<Integer,Map<String,String[]>> answerMap = (Map<Integer, Map<String, String[]>>) sessionMap.get("AnswerMap");
		Map<String,String[]> pageMap = new HashMap<String,String[]>();
		this.pageCount = this.surveyService.getSurveyPageCount(this.surveyId);
		for(String key : this.parameters.keySet()){
			if(key.startsWith("submit")){
				if(key.endsWith("pre")){
					this.curPage=this.surveyService.getCurPage(this.surveyId,this.orderNo-1);
				}else if(key.endsWith("next")){
					this.curPage=this.surveyService.getCurPage(this.surveyId,this.orderNo+1);			
				}else if(key.endsWith("done")){
					
				}else if(key.endsWith("cancel")){
					
				}
			}else if(key.startsWith("q_")){
				pageMap.put(key, this.parameters.get(key));
			}
		}
		answerMap.put(this.orderNo, pageMap);
		sessionMap.put("AnswerMap", answerMap);
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

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
