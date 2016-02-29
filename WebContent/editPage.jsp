<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>新增调查页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/PageAction_saveOrUpdatePage">
		<input type="hidden" name="surveyId"  value="${surveyId}"/>  <br/>
		<input type="hidden" name="id"  value="${pageId}"/>  <br/>
		<input type="hidden" name="orderNo"  value="${model.orderNo}"/>  <br/>
		标题:<input type="text" name="title"  value="${model.title}"/>  <br/>
		描述:<input type="text" name="description"  value="${model.description}"/>  <br/>
		<input type="submit" value="确定">
	</form>
</body>
</html>