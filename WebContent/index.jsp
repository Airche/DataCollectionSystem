<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
</head>
<body>
	<c:if test="${user==null }">
	<form action="${pageContext.request.contextPath}/LoginAction_doLogin" method="post">
		登录邮箱:<input type="text" name="email"  value="${email}"/>		${errors.email }	<br/>
		登录密码:<input type="password" name="password" value="${password}"/>		${errors.password }	<br/>
		<input type="submit" value="登录" />
		<s:debug></s:debug>
	</form>
	</c:if>
	<c:if test="${user!=null}">
		欢迎:${user.nickName }
	</c:if>
</body>
</html>