<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户注册</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/RegAction_doReg" method="post">
		登录邮箱:<input type="text" name="email"  value="${email}"/>		${errors.email }	<br/>
		登录密码:<input type="password" name="password" value="${password}"/>		${errors.password }	<br/>
		确认密码:<input type="password" name="confirmPassword" value="${confirmPassword}"/>	${errors.confirmPassword }<br/>
		显示昵称:<input type="text" name="nickName"  value="${nickName}"/>		${errors.nickName }	<br/>
		<input type="submit" value="确定" />
		<s:debug></s:debug>
	</form>
</body>
</html>