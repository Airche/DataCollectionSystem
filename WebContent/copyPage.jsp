<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>复制页面</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/PageAction_doCopyPage" method="post">
		<input type="hidden" name="pageId" value="${pageId}">
		<select name="surveyId">
			<c:forEach items="${surveys}" var="s">
				调查编号<option>${s.id}</option>
			</c:forEach>
		</select>
		<input type="submit"  value="提交"/>
	</form>
</body>
</html>