<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限列表</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<a href="${pageContext.request.contextPath}/RightAction_toAddRightPage">增加权限</a> <br/>
	<c:if test="${empty allRights}">
		您没有任何权限！		<br/>
	</c:if>
	<c:if test="${!empty allRights}">
		<form action="${pageContext.request.contextPath}/RightAction_batchUpdateRights" method="post">
				<c:forEach items="${allRights}" var="right" varStatus="status">
			 	权限id:<input type="text" name="allRights[${status.index}].Id"  value="${allRights[status.index].id}" readonly="readonly"/>&nbsp;
				权限名称:<input type="text" name="allRights[${status.index}].rightName"  value="${allRights[status.index].rightName}"/>&nbsp;
				权限URL:<input type="text" name="allRights[${status.index}].rightUrl"  value="${allRights[status.index].rightUrl}"/>&nbsp;
				权限描述:<input type="text" name="allRights[${status.index}].rightDesc"  value="${allRights[status.index].rightDesc}"/>&nbsp;
				权限代码:${right.rightCode}&nbsp;
				权限位:${right.rightPos}
				<a href="${pageContext.request.contextPath}/RightAction_toEditRightPage?rightId=${right.id}">编辑</a>
				<a href="${pageContext.request.contextPath}/RightAction_deleteRight?rightId=${right.id}">删除</a>
				<br/>
			</c:forEach>

			<input type="submit" value="提交"/>  
		</form>
	</c:if>|
</body>
</html>