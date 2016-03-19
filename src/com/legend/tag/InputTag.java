package com.legend.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


import com.legend.util.validateUtil;

public class InputTag extends TagSupport {
	
	private String type;
	private String value;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		if(!hashRight()){
			return SKIP_BODY;
		}
		JspWriter out = this.pageContext.getOut();
		 try {
			out.println("<input type=\""+this.type+"\"  value=\"" + this.value +"\" />");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return super.doStartTag();
	}

	private boolean hashRight() {
		FormTag tag  = (FormTag) this.getParent();
		String href = null;
		while(tag!=null){
			if(tag instanceof FormTag){
				
				href = tag.getAction();
				break;
			}
			tag = (FormTag) tag.getParent();
		}
		String contextPath = this.pageContext.getServletContext().getContextPath();
		String actionName = href.substring(href.lastIndexOf('/')+1, href.length());
		String ns = href.substring(contextPath.length(), href.lastIndexOf(actionName));
		return validateUtil.hasRight(ns, actionName, (HttpServletRequest)this.pageContext.getRequest());
	}
	
}
