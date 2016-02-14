<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<br /> ${model.id} ${model.title}<br/>
	<c:forEach var="p" items="${model.pages}">
 		 页面标题:${p.title}<br/>
 		 <c:forEach var="q"  items="$(p.questions)">
 		 	问题类型:${q.questionType}<br/>
 		 	<c:forEach var="opt" items="${fn:split(q.options, '-')}">
 		 		选项名称:${opt}
 		 	</c:forEach>
 		 </c:forEach>
	</c:forEach>
	<s:debug></s:debug>
</body>
</html>