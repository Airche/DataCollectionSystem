<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的调查</title>
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
				<th>状态</th>
				<th>设计调查</th>
				<th>编辑调查</th>
				<th>删除调查</th>
				<th>打开/关闭</th>
				<th>清除调查</th>
			</tr>
			<c:forEach items="${mySurveys}" var="s">
				<%--JSTL，EL表达式 --%>
				<tr>
					<td>${s.id }</td>
					<td>${s.title }</td>
					<td><fmt:formatDate value="${s.createDate }"
							pattern="yyyy-MM-dd HH:MM" /></td>
					<td>
					<c:if test="${s.closed}">
							开启
					</c:if>
					<c:if test="${!s.closed}">
							关闭
					</c:if>
					</td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_getSurvey?surveyId=${s.id}">设计</a></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_editSurvey?surveyId=${s.id}">编辑</a></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_deleteSurvey?surveyId=${s.id}">删除</a></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_toogleStatus?surveyId=${s.id}">打开/关闭</a></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_clearAnswers?surveyId=${s.id}">清除调查</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>