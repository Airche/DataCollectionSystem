<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>角色列表</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<a href="${pageContext.request.contextPath}/RoleAction_toAddRolePage">增加角色</a> <br/>
	<c:if test="${empty allRoles}">
		没有任何角色！		<br/>
	</c:if>
	<c:if test="${!empty allRoles}">
				<c:forEach items="${allRoles}" var="role" varStatus="status">
					角色id:${role.id}&nbsp;
					角色名称:${role.roleName}&nbsp;
					角色值:${role.roleValue}&nbsp;
					角色描述:${role.roleDesc}&nbsp;
				<a href="${pageContext.request.contextPath}/RoleAction_toEditRolePage?roleId=${role.id}">编辑</a>
				<a href="${pageContext.request.contextPath}/RoleAction_deleteRole?roleId=${role.id}">删除</a>
				<br/>
			</c:forEach>
	</c:if>|
</body>
</html>