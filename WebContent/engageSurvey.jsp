<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>进行调查</title>
</head>
<body>
	<jsp:include page="head.jsp" />
	调查标题：${CurSurvey.title}  <br/>
	
	页标题：${curPage.id}	 ${curPage.title}	 <br/>
	<c:forEach items="${curPage.questions}" var="q">
	 		 	问题类型:${q.id} ${q.title}		
 		 	<c:forEach var="opt" items="${fn:split(q.options, '-')}">
 		 		选项名称:${opt}
 		 	</c:forEach>
 		 	<br/>
	</c:forEach>
	
	<c:if test="${curPage.orderNo>1}">
		<input type="submit"  value="上一步">
	</c:if>
	
	<c:if test="${curPage.orderNo<pageCount}">
		<input type="submit"  value="下一步">
	</c:if>
	
	
</body>
</html>