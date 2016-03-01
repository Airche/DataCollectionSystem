package com.legend.model;

import java.io.Serializable;

public class Question implements Serializable {
	private Integer id;
	//题型0-8
	private Integer questionType;
	private String title;
	private String options;
	private Boolean other;
	private Integer otherStyle;
	private String otherSelectOptions;
	private String matrixRowTitles;
	private String matrixColTitles;
	private String matrixSelectTitles;
	private Page page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Boolean getOther() {
		return other;
	}

	public void setOther(Boolean other) {
		this.other = other;
	}

	public Integer getOtherStyle() {
		return otherStyle;
	}

	public void setOtherStyle(Integer otherStyle) {
		this.otherStyle = otherStyle;
	}

	public String getOtherSelectOptions() {
		return otherSelectOptions;
	}

	public void setOtherSelectOptions(String otherSelectOptions) {
		this.otherSelectOptions = otherSelectOptions;
	}

	public String getMatrixRowTitles() {
		return matrixRowTitles;
	}

	public void setMatrixRowTitles(String matrixRowTitles) {
		this.matrixRowTitles = matrixRowTitles;
	}

	public String getMatrixColTitles() {
		return matrixColTitles;
	}

	public void setMatrixColTitles(String matrixColTitles) {
		this.matrixColTitles = matrixColTitles;
	}

	public String getMatrixSelectTitles() {
		return matrixSelectTitles;
	}

	public void setMatrixSelectTitles(String matrixSelectTitles) {
		this.matrixSelectTitles = matrixSelectTitles;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
