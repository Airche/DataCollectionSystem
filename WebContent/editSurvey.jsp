<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设计调查</title>
</head>
<body>

	<jsp:include page="head.jsp" />
	<form action="${pageContext.request.contextPath}/SurveyAction_saveSurvey" method="post">
			<input type="hidden"  name="surveyId" value="${model.id}">
			调查标题:<input type="text"  name="title" value="${model.title}"> <br/>
			上一步提示:<input type="text" name="preText" value="${model.preText}">	<br/>
			下一步提示:<input type="text" name="nextText" value="${model.nextText}">	<br/>
			退出提示:<input type="text" name="exitText" value="${model.exitText}">	<br/>
			完成提示:<input type="text" name="doneText" value="${model.doneText}">	<br/>
			<input type="submit" value="确定">
	</form>
	
</body>
</html>