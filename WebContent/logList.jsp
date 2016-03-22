<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日志列表</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<c:if test="${empty allLogs}">
		没有任何日志！		<br/>
	</c:if>
	<c:if test="${!empty allLogs}">
			<table border="1">
				<thead>
					<tr>
						<th>日志id</th>
						<th>操作人</th>
						<th>动作名称</th>
						<th>动作参数</th>
						<th>操作结果</th>
						<th>结果信息</th>
						<th>操作时间</th>
					</tr>
				</thead>
				<c:forEach items="${allLogs}" var="log">
					<tr>
			 			<td>${log.id}</td>
			 			<td>${log.operator}</td>
			 			<td>${log.operName}</td>
			 			<td>${log.operParams}</td>
			 			<td>${log.operResult}</td>
			 			<td>${log.resultMsg}</td>
			 			<td>${log.operTime}</td>
					</tr>
				</c:forEach>
			</table>
	</c:if>|
</body>
</html>