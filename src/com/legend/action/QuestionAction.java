package com.legend.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Page;
import com.legend.model.Question;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("questionAction")
@Scope("prototype")
public class QuestionAction extends BaseAction<Question> implements UserAware{
	
	private User user;
	private int surveyId;
	private int pageId;
	private int questionId;

	@Resource(name = "surveyService")
	private SurveyService surveyService;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public String toSelectQuestionType() {
		return "selectQuestionTypePage";
	}

	public String toDesignQuestionPage(){
		return ""+model.getQuestionType();
	}
	
	public String saveOrUpdate(){
		Page page = new Page();
		page.setId(pageId);
		model.setPage(page);
		this.surveyService.saveOrUpdateQuestion(model);
		return "designSurveysAction";
	}
	
	public String deleteQuestion(){
		this.surveyService.deleteQuestion(questionId);
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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	
}
