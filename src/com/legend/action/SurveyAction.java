package com.legend.action;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletConfigAware;

import com.legend.action.intercepor.UserAware;
import com.legend.model.Survey;
import com.legend.model.User;
import com.legend.service.SurveyService;

@Controller("surveyAction")
@Scope("prototype")
public class SurveyAction extends BaseAction<Survey> implements UserAware,ServletContextAware{
	
	private Map<String, Object> sessionMap;
	private List<Survey> mySurveys;
	private int curPage;
	private int pageSize;
	private int pageCount;
	private int nextForward ;
	private int surveyId;
	private File logo;
	private String logoFileName;
	private ServletContext servletContext;
	private String inputPage;
	
	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

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
			this.curPage += 1;
			this.mySurveys = this.surveyService.findMySurveysForPage(user,curPage,pageSize);
		}else if(nextForward<0){
			this.curPage -= 1;
			this.mySurveys = this.surveyService.findMySurveysForPage(user,curPage,pageSize);
		}
		return "mySurveyListPageForPage";
	}
	
	public String newSurvey(){
		this.model = this.surveyService.newSurvey(user);
		return "designSurveysPage";
	}
	
	public String editSurvey(){
		this.model = this.surveyService.getSurvey(this.surveyId);
		return "editSurveysPage";
	}
	
	public String saveSurvey(){
		model.setId(surveyId);
		model.setUser(user);
		model.setCreateDate(new Date());
		this.surveyService.update(model);
		return "designSurveysAction";
	}
	
	public String getSurvey(){
		this.model =  this.surveyService.getSurveyWithChildren(this.surveyId);
		return "designSurveysPage";
	}
	
	public String deleteSurvey(){
		this.surveyService.deleteSurvey(this.surveyId);
		return "findMySurveyAction";
	}
	
	public String clearAnswers(){
		this.surveyService.clearAnswer(this.surveyId);
		return "findMySurveyAction";
	}
	
	public String toogleStatus(){
		this.surveyService.toogleStatus(this.surveyId);
		return "findMySurveyAction";		
	}
	
	public String toAddLogoPage(){
		return "addLogoPage";
	}
	
	//在prepare拦截器中为inputPage赋值
	public void prepareAddLogo(){
		this.inputPage="/addLogo.jsp";		
	}
	
	public String addLogo(){
		String newFileName = System.nanoTime()+this.logoFileName.substring(this.logoFileName.lastIndexOf("."));
		String newPathName = this.servletContext.getRealPath("\\upload")+"\\"+newFileName;
		File newFile = new File(newPathName);
		logo.renameTo(newFile);
		this.surveyService.addLogoPath(this.surveyId,"\\upload\\"+newFileName);
		return "designSurveysAction";
	}
	
	public boolean fileExist(String logoPhtotPath){
		if(logoPhtotPath!=null&&logoPhtotPath.length()>0){
			//直接调用方法时，this.servletContext不会自动注入，所以选择使用ServletActionContext获取servletContext
			String newPathName = ServletActionContext.getServletContext().getRealPath("")+logoPhtotPath;
			File newFile = new File(newPathName);
			return newFile.exists();
		}
		return false;
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

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public File getLogo() {
		return logo;
	}

	public void setLogo(File logo) {
		this.logo = logo;
	}

	public String getLogoFileName() {
		return logoFileName;
	}

	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
}
