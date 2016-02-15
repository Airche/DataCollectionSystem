package com.legend.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Page;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.PageService;
import com.legend.service.SurveyService;

@Controller("pageAction")
@Scope("prototype")
public class PageAction extends BaseAction<Page> implements UserAware{
	
	private User user;
	private int surveyId;
	private int pageId;
	
	@Resource(name="pageService")
	private PageService pageService;
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public String toAddPagePage(){
		return "addPage";
	}
	
	public String editPagePage(){
		this.model = this.pageService.getPageById(this.pageId);
		return "editPage";
	}
	
	public String saveOrUpdatePage(){
		Survey survey = new Survey();
		survey.setId(this.surveyId);
		this.model.setSurvey(survey);
		this.pageService.saveOrUpdate(model);
		return "designSurveysAction";
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	
}
