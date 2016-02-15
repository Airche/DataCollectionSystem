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
				<th>操作</th>
			</tr>
			<c:forEach items="${mySurveys}" var="s">
				<%--JSTL，EL表达式 --%>
				<tr>
					<td>${s.id }</td>
					<td>${s.title }</td>
					<td><fmt:formatDate value="${s.createDate }"
							pattern="yyyy-MM-dd HH:MM" /></td>
					<td><a href="${pageContext.request.contextPath}/SurveyAction_getSurvey?surveyId=${s.id}">设计</a></td>
				</tr>
			</c:forEach>
		</table>
		当前页数:<input type="text" name="curPage"  value=${curPage} readonly="readonly"  style="width:15px;" >
		<input type="hidden" name="pageSize" value=${pageSize} >
		总页数:<input type="text" name="pageCount" value=${pageCount} readonly="readonly" style="width:15px;" >
		 <c:if test="${curPage>1}">
			 <a href="${pageContext.request.contextPath}/SurveyAction_mySurveysForPage?curPage=${curPage}&pageSize=${pageSize}&pageCount=${pageCount}&nextForward=-1">上一页</a>	
		 </c:if>	
		 <c:if test="${curPage<pageCount}">
			 <a href="${pageContext.request.contextPath}/SurveyAction_mySurveysForPage?curPage=${curPage}&pageSize=${pageSize}&pageCount=${pageCount}&nextForward=1">下一页</a>		
		 </c:if>	
		
	</c:if>
</body>
</html>