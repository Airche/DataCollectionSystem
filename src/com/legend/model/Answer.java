package com.legend.model;

import java.util.Date;

public class Answer {
	private Integer id;
	private String answerIds;
	private String otherAnswer;
	private String uuid;
	private Date answerTime;
	private Integer questionId;
	private Integer surveyId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAnswerIds() {
		return answerIds;
	}
	public void setAnswerIds(String answerIds) {
		this.answerIds = answerIds;
	}
	public String getOtherAnswer() {
		return otherAnswer;
	}
	public void setOtherAnswer(String otherAnswer) {
		this.otherAnswer = otherAnswer;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	
	
}
