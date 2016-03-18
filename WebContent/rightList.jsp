<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>权限列表</title>
</head>
<script language="javascript" >
function changevalue(name){
	 var obj=document.getElementById(name)
	if(obj.checked){
		obj.setAttribute("value", true);
	}else{
		obj.setAttribute("value", false);
	}
}
</script>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<a href="${pageContext.request.contextPath}/RightAction_toAddRightPage">增加权限</a> <br/>
	<c:if test="${empty allRights}">
		您没有任何权限！		<br/>
	</c:if>
	<c:if test="${!empty allRights}">
		<form action="${pageContext.request.contextPath}/RightAction_batchUpdateRights" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>权限id</th>
						<th>权限名称</th>
						<th>权限URL</th>
						<th>权限描述</th>
						<th>是否公共</th>
						<th>权限代码</th>
						<th>权限位</th>
						<th>编辑权限</th>
						<th>删除权限</th>
					</tr>
				</thead>
				<c:forEach items="${allRights}" var="right" varStatus="status">
					<tr>
			 			<td><input type="text" name="allRights[${status.index}].Id"  value="${allRights[status.index].id}" readonly="readonly"/></td>
						<td><input type="text" name="allRights[${status.index}].rightName"  value="${allRights[status.index].rightName}"/></td>
						<td><input type="text" name="allRights[${status.index}].rightUrl"  value="${allRights[status.index].rightUrl}" /></td>
						<td><input type="text" name="allRights[${status.index}].rightDesc"  value="${allRights[status.index].rightDesc}"/></td>
						<td><input type="checkbox" name="allRights[${status.index}].common"  id="allRights[${status.index}].common" 
				                 value="${allRights[status.index].common}"  onclick=changevalue(this.name)
				                ${allRights[status.index].common?"checked":""}/></td>
						<td>${right.rightCode}</td>
						<td>${right.rightPos}</td>
						<td><a href="${pageContext.request.contextPath}/RightAction_toEditRightPage?rightId=${right.id}">编辑权限</a></td>
						<td><a href="${pageContext.request.contextPath}/RightAction_deleteRight?rightId=${right.id}">删除权限</a></td>
					</tr>
				</c:forEach>
			</table>

			<input type="submit" value="提交"/>  
		</form>
	</c:if>|
</body>
</html>