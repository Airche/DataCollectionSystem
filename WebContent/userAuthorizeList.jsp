<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户权限管理</title>
</head>
<body>
	<jsp:include page="/head.jsp" /><br/>

	<table border="1">
		<thead>
			<tr>
				<th>编号</th>
				<th>邮件</th>
				<th>用户名</th>
				<th>昵称</th>
				<th>修改授权</th>
				<th>删除授权</th>
			</tr>
		</thead>
			<c:forEach items="${allUsers}" var="user">
				<tr>
					<td>${user.id }</td>
					<td>${user.email }</td>
					<td>${user.name }</td>
					<td>${user.nickName }</td>
					<td><a href="${pageContext.request.contextPath}/UserAuthorizeAction_toEditAuthorize?userId=${user.id}">修改授权</a></td>
					<td><a href="${pageContext.request.contextPath}/UserAuthorizeAction_clearAuthorize?userId=${user.id}">删除授权</a></td>
				</tr>
			</c:forEach>
	</table>
	
</body>
</html>