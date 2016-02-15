<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="head.jsp" />
	<%--
		<s:iterator value="mySurveys" var="s">
			<s:property name="#s.title"/>		OGNL表达式 
		</s:iterator>
	--%>
	<c:if test="${mySurveys==null }">
		您没有任何调查项
	</c:if>
	<c:if test="${mySurveys!=null }">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>调查标题</th>
				<th>创建时间</th>
				<th>设计调查</th>
				<th>编辑调查</th>
			</tr>
			<c:forEach items="${mySurveys}" var="s">
				<%--JSTL，EL表达式 --%>
				<tr>
					<td>${s.id }</td>
					<td>${s.title }</td>
					<td><fmt:formatDate value="${s.createDate }"
							pattern="yyyy-MM-dd HH:MM" /></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_getSurvey?surveyId=${s.id}">设计</a></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_editSurvey?surveyId=${s.id}">编辑</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>