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
			<table border="1">
				<thead>
					<tr>
						<th>角色id</th>
						<th>角色名称</th>
						<th>角色值</th>
						<th>角色描述</th>
						<th>编辑角色</th>
						<th>删除角色</th>
					</tr>
				</thead>
				<c:forEach items="${allRoles}" var="role" varStatus="status">
					<tr>
						<td>${role.id}</td>
						<td>${role.roleName}</td>
						<td>${role.roleValue}</td>
						<td>${role.roleDesc}</td>
						<td><a href="${pageContext.request.contextPath}/RoleAction_toEditRolePage?roleId=${role.id}">编辑角色</a></td>
						<td><a href="${pageContext.request.contextPath}/RoleAction_deleteRole?roleId=${role.id}">删除角色</a></td>
					</tr>
				</c:forEach>
			</table>
	</c:if>|
</body>
</html>