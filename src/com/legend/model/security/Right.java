package com.legend.model.security;

import java.util.HashSet;
import java.util.Set;

public class Right {
	private Integer id;
	private String rightName = "未命名";
	private String rightUrl;
	private String rightDesc;
	private Long rightCode;		//权限码
	private Integer rightPos;				//权限位
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
	}
	public String getRightUrl() {
		return rightUrl;
	}
	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}
	public String getRightDesc() {
		return rightDesc;
	}
	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}
	public Long getRightCode() {
		return rightCode;
	}
	public void setRightCode(Long rightCode) {
		this.rightCode = rightCode;
	}
	public Integer getRightPos() {
		return rightPos;
	}
	public void setRightPos(Integer rightPos) {
		this.rightPos = rightPos;
	}
	
}
