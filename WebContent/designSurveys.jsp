<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设计调查</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br />
	 ${model.id} ${model.title}	
	<c:set var="pageCount" value="${fn:length(model.pages)}" />  
	页面个数：${pageCount}
	<jsp:useBean id="surveyAction" class="com.legend.action.SurveyAction" scope="page" />
	<c:if test="${surveyAction.fileExist(model.logoPhotoPath)}">
		<img  src="${pageContext.request.contextPath}${model.logoPhotoPath}" height="20px" width="15pxs"/>
	</c:if>
	<a href="${pageContext.request.contextPath}/SurveyAction_toAddLogoPage?surveyId=${model.id}">增加LOGO</a>
	<a href="${pageContext.request.contextPath}/PageAction_toAddPagePage?surveyId=${model.id}&orderNo=${pageCount+1}">增加页</a>
	<br/>
	<c:forEach var="p" items="${model.pages}">
	<br/>
 		 页面标题:${p.id} ${p.title}	s
 		  <a href="${pageContext.request.contextPath}/PageAction_deletePage?surveyId=${model.id}&pageId=${p.id}">删除页面</a>		   
 		 <a href="${pageContext.request.contextPath}/PageAction_toEditPagePage?surveyId=${model.id}&pageId=${p.id}">编辑页</a> 
 		 <a href="${pageContext.request.contextPath}/PageAction_toCopyPagePage?surveyId=${model.id}&pageId=${p.id}">复制页</a> 
 		 <a href="${pageContext.request.contextPath }/QuestionAction_toSelectQuestionType?surveyId=${model.id}&pageId=${p.id}">增加问题</a>
 		 <c:if test="${p.orderNo gt 1}">
			<a href="${pageContext.request.contextPath}/PageAction_moveUpPage?surveyId=${model.id}&pageCount=${pageCount}&orderNo=${p.orderNo}">上移</a>
		 </c:if>
 		 <c:if test="${p.orderNo lt pageCount}">
			<a href="${pageContext.request.contextPath}/PageAction_moveDownPage?surveyId=${model.id}&pageCount=${pageCount}&orderNo=${p.orderNo}">下移</a>
		</c:if>
 		 <br/> 
 		 <c:forEach var="q"  items="${p.questions}">
 		 	问题类型:${q.id} ${q.title}		
 		 		<a href="${pageContext.request.contextPath}/QuestionAction_toEditQuestionPage?surveyId=${model.id}&pageId=${p.id}&id=${q.id}">编辑问题</a>
 		 		<a href="${pageContext.request.contextPath}/QuestionAction_deleteQuestion?surveyId=${model.id}&questionId=${q.id}">删除问题</a>  <br/>
 		 	<c:forEach var="opt" items="${fn:split(q.options, '-')}">
 		 		选项名称:${opt}
 		 	</c:forEach>
 		 	<br/>
 		 </c:forEach>
	</c:forEach>
	<s:debug></s:debug>
</body>
</html>