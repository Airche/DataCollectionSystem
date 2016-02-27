<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加LOGO</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/SurveyAction_addLogo" method="post" enctype="multipart/form-data">
		<input type="hidden" name="surveyId" value="${surveyId}"/>
		<input type="file" name="logo"><br> 
		<input type="submit" 	value="确定">
	</form>
</body>
</html>