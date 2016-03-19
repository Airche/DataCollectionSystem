package com.legend.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.legend.util.validateUtil;

public class HyperLinkTag extends TagSupport {
	
	private String href;
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public int doStartTag() throws JspException {
		if(!hashRight()){
			return SKIP_BODY;
		}
		JspWriter out = this.pageContext.getOut();
		 try {
			out.print("<a href=\""+this.href+"\">");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return super.doEndTag();
	}

	private boolean hashRight() {
		String contextPath = this.pageContext.getServletContext().getContextPath();
		String actionName = this.href.substring(this.href.lastIndexOf('/')+1, this.href.length());
		String ns = this.href.substring(contextPath.length(), this.href.lastIndexOf(actionName));
		return validateUtil.hasRight(ns, actionName, (HttpServletRequest)this.pageContext.getRequest());
	}

	@Override
	public int doEndTag() throws JspException {
		if(!hashRight()){
			return SKIP_BODY;
		}
		JspWriter out = this.pageContext.getOut();
		 try {
			out.println("</a>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return super.doStartTag();
	}

}
