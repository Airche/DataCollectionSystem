<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>进行调查</title>
</head>
<body>
	<jsp:include page="head.jsp" />
	<jsp:useBean id="engageSurveyAction" class="com.legend.action.EngageSurveyAction" scope="page" />
	调查标题：${CurSurvey.title}  <br/><br/>
	
	<form action="${pageContext.request.contextPath}/EngageSurveyAction_doEngageSurvey" method="post">
		<input type="hidden" name="surveyId" value="${surveyId}"/>
		<input type="hidden" name="orderNo" value="${curPage.orderNo}"/>
		<input type="hidden" name="pageCount" value="${pageCount}"/>
		${curPage.title}：	 <br/>
		<c:set var="orderNo" value="${curPage.orderNo}"/>
		<c:set var="AnswerMap" value="${session.AnswerMap}"/>
		
		<c:forEach items="${curPage.questions}" var="q" >
	 		 	<br/>${q.title}		<br/>
 		 	<c:forEach var="opt" items="${fn:split(q.options, '-')}">
 		 		<c:set var="q_name" value="q_${q.id}"/>
 		 		<c:set var="sessionOptValues" value="${AnswerMap[orderNo][q_name]}"/>
 		 		<c:if test="${q.questionType<=3}">		<%--前3题的题型类似 --%>
			 		 	<input type=${q.questionType==1?"radio":"checkbox"} value="${opt}" name="${q_name}"  ${engageSurveyAction.chkContain(sessionOptValues,opt)?"checked":""}>${opt}</input>
	 					<c:if test="${q.questionType==2}">			
 		 						<br/>	
 		 				</c:if>
	 			</c:if>
	 			<c:if test="${q.questionType==4}">	
			 		 	<input type="text"  value="${AnswerMap[orderNo][q_name][0]}" name="${q_name}" />
	 			</c:if>
 		 	</c:forEach>
 		 	<br/>
		</c:forEach>
	
		<c:if test="${curPage.orderNo>1}">
			<input type="submit"  name="submit_pre" value="上一步"/>
		</c:if>
	
		<c:if test="${curPage.orderNo<pageCount}">
			<input type="submit"  name="submit_next"  value="下一步"/>
		</c:if>
		
		<c:if test="${curPage.orderNo==pageCount}">
			<input type="submit"  name="submit_done"  value="完成"/>
		</c:if>
			<input type="submit"  name="submit_cancel"  value="取消"/>
	
	</form>
	
	<s:debug/>
</body>
</html>