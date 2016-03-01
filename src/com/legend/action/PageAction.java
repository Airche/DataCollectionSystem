package com.legend.action;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Page;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("pageAction")
@Scope("prototype")
public class PageAction extends BaseAction<Page> implements UserAware{
	
	private User user;
	private int surveyId;
	private int pageId;
	private int pageCount;
	private List<Survey> surveys;
	
	@Resource(name="surveyService")
	private SurveyService surveyService;
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public String toAddPagePage(){
		return "addPage";
	}
	
	public String toEditPagePage(){
		this.model = this.surveyService.getPageById(this.pageId);
		return "editPage";
	}
	
	public String saveOrUpdatePage(){
		Survey survey = new Survey();
		survey.setId(this.surveyId);
		this.model.setSurvey(survey);
		if(model.getId()==0){
			model.setId(null);
		}
		this.surveyService.saveOrUpdatePage(model);
		return "designSurveysAction";
	}
	
	public String moveUpPage(){
		if(model.getOrderNo()>1){			
			this.surveyService.switchPageOrderNo(this.surveyId,model.getOrderNo(),model.getOrderNo()-1);
		}
		return "designSurveysAction";
	}
	
	public String moveDownPage(){
		if(model.getOrderNo()<pageCount){			
			this.surveyService.switchPageOrderNo(this.surveyId,model.getOrderNo(),model.getOrderNo()+1);
		}
		return "designSurveysAction";
	}
	
	public String deletePage(){
		this.surveyService.deletePage(pageId);
		return "designSurveysAction";
	}
	
	public String toCopyPagePage(){
		surveys = this.surveyService.findMySurveys(user);
		Iterator<Survey> is = surveys.iterator();
		while(is.hasNext()){
			Survey survey= is.next();
			if(survey.getId() == this.surveyId){
				is.remove();
			}
		}
		return "copyPage";
	}
	
	public String doCopyPage(){
		this.surveyService.copyPage(this.surveyId,this.pageId);
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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Survey> getSurveys() {
		return surveys;
	}

	public void setSurveys(List<Survey> surveys) {
		this.surveys = surveys;
	}

	
	
}
