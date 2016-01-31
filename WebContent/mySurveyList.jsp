<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<%--
	<s:iterator value="mySurveys" var="s">
		<s:property name="#s.title"/>		OGNL表达式 
	</s:iterator>
--%>
	<c:if test="${mySurveys==null }">
		您没有任何调查项
	</c:if>
	<c:if test="${mySurveys!=null }">
		<c:forEach items="${mySurveys}" var="s">
			<%--JSTL，EL表达式 --%>
		${s.title }
	</c:forEach>
	</c:if>
</body>
</html>