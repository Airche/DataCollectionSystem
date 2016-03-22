package com.legend.model;

import java.util.Date;

public class Log extends BaseEntity {

	private Integer id;
	private String operator;
	private String operName;
	private String operParams;
	private String operResult;
	private String resultMsg;
	private Date operTime = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperParams() {
		return operParams;
	}

	public void setOperParams(String operParams) {
		this.operParams = operParams;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", operator=" + operator + ", operName=" + operName + ", operParams=" + operParams
				+ ", operResult=" + operResult + ", resultMsg=" + resultMsg + ", operTime=" + operTime + "]";
	}
	
	

}
