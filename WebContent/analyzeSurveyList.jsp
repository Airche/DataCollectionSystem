<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>分析调查</title>
</head>
<body>
	<jsp:include page="head.jsp" />
	${model.title}	<br/><br/>
	<c:forEach items="${model.pages}" var="page">
		&nbsp ${page.title}  ---------------------<br/>
		<c:forEach items="${page.questions}" var="question">
			<form action="${pageContext.request.contextPath}/ChartOutputAction" method="post">
				&nbsp ${question.title }	
				<input type="hidden" name="questionId" value="${question.id }"/>
				<select name="charType">
					<option value="0">平面饼图</option>
					<option value="1">立体饼图</option>
					<option value="2">横向平面柱状图</option>
					<option value="3">纵向平面柱状图</option>
					<option value="4">横向立体柱状图</option>
					<option value="5">纵向立体柱状图</option>
					<option value="6">平面折线图</option>
					<option value="7">立体折线图</option>
				</select>	
				<input type="submit" value="查看"/>
			</form>
		</c:forEach>
	</c:forEach>
</body>
</html>