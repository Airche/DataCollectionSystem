<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加问题</title>
</head>
<body>
		<jsp:include page="head.jsp" />
	
	<form action="${pageContext.request.contextPath}/QuestionAction_saveOrUpdate?surveyId=${surveyId}&pageId=${pageId}" method="post">
		<input type="hidden" name="questionType" value="0"/>
		<input type="text" name="title"/>
		
		<input type="submit" value="确定"/>
	</form>
</body>
</html>