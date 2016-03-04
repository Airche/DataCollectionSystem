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
public class EngageSurveyAction extends BaseAction<Survey> implements UserAware, SessionAware, ParameterAware {

	private static final String ANSWERMAP = "AnswerMap";
	private static final String CURSURVEY = "CurSurvey";
	private User user;
	private List<Survey> surveys;
	private int surveyId;
	private Page curPage;
	private int orderNo;
	private Map<String, Object> sessionMap;
	private int pageCount;
	private Map<String, String[]> parameters;

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	public String toAvailableSurveyPage() {
		this.surveys = this.surveyService.findMyAvailableSurveys(user);
		return "availableSurveyPage";
	}

	public String entry() {
		Survey survey = this.surveyService.getSurvey(this.surveyId);
		sessionMap.put(CURSURVEY, survey);
		this.pageCount = this.surveyService.getSurveyPageCount(this.surveyId);
		this.curPage = this.surveyService.getFirstPage(this.surveyId);
		return "engageSurveyPage";
	}

	public String doEngageSurvey() {

		Map<Integer, Map<String, String[]>> answerMap = (Map<Integer, Map<String, String[]>>) sessionMap.get("AnswerMap");
		if (answerMap == null) {
			answerMap = new HashMap<Integer, Map<String, String[]>>();
		}
		Map<String, String[]> pageMap = new HashMap<String, String[]>();
		String submit = null;
		for (String key : this.parameters.keySet()) {
			if (key.startsWith("q_")) {
				pageMap.put(key, this.parameters.get(key));
			} else if (key.startsWith("submit")) {
				if (key.endsWith("pre")) {
					submit = "pre";
				} else if (key.endsWith("next")) {
					submit = "next";
				} else if (key.endsWith("done")) {
					submit = "done";
				} else if (key.endsWith("cancel")) {
					submit = "cancel";
				}
			}
		}
		answerMap.put(this.orderNo, pageMap);
		sessionMap.put(ANSWERMAP, answerMap);

		if (submit.endsWith("pre")) {
			this.curPage = this.surveyService.getCurPage(this.surveyId, this.orderNo - 1);
		} else if (submit.endsWith("next")) {
			this.curPage = this.surveyService.getCurPage(this.surveyId, this.orderNo + 1);
		} else if (submit.endsWith("done")) {
			this.surveyService.saveAnswer(this.surveyId,  (Map<Integer, Map<String, String[]>>) sessionMap.get(ANSWERMAP));
			clearSessionData();
			return "toAvailableSurveyPageAction";
		} else if (submit.endsWith("cancel")) {
			clearSessionData();
			return "engageSurveyPageAction";
		}

		return "engageSurveyPage";
	}

	private void clearSessionData() {
		sessionMap.remove(ANSWERMAP);
		sessionMap.remove(CURSURVEY);
	}
	
	public boolean chkContain(String[] sessionOptValues,String opt){
		if(sessionOptValues!=null&&sessionOptValues.length>0&&opt!=null){
			for(String sessionOptValue : sessionOptValues){
				if(sessionOptValue.equals(opt)){
					return true;
				}
			}
		}
		return false;
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
