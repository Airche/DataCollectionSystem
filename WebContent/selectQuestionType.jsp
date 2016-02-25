<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>选择提型</title>
</head>
<body>
	<jsp:include page="head.jsp" />
	<form action="${pageContext.request.contextPath}/QuestionAction_toDesignQuestionPage" method="post">
		<input type="text" name="surveyId" value="${surveyId }"/>
		<input type="text" name="pageId" value="${pageId }"/>
		<select name="questionType">
			<option selected="selected">--请选择问题题型--</option>
			<option value="0">非矩阵式横向单选按钮</option>
			<option value="1">非矩阵式纵向单选按钮</option>
			<option value="2">非矩阵式横向复选按钮</option>
			<option value="3">非矩阵式纵向复选按钮</option>
			<option value="4">非矩阵式下拉列表</option>
			<option value="5">非矩阵式文本框</option>
			<option value="6">矩阵式单选按钮</option>
			<option value="7">矩阵式复选按钮</option>
			<option value="8">矩阵式下拉列表</option>
		</select>
		<input type="submit" value="确定"/>
	</form>
</body>
</html>