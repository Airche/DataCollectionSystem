<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>可参与调查列表</title>
</head>
<body>
	<jsp:useBean id="surveyAction" class="com.legend.action.SurveyAction" scope="page" />
	<c:set var="pageCount" value="${fn:length(surveys)}" />
	<c:set var="cells" value="5" />
	
	<table border="1">

		<c:forEach var="i" begin="0" end="${pageCount}" step="${cells}">
			<tr>
				<c:forEach var="j" begin="0" end="${cells-1}">
						<td>
							<c:if test="${surveyAction.fileExist(surveys[i+j].logoPhotoPath)}">
							</c:if>
							<c:choose>  
   								<c:when test="${surveyAction.fileExist(surveys[i+j].logoPhotoPath)}">
									<img  src="${pageContext.request.contextPath}${surveys[i+j].logoPhotoPath}" height="40px" width="50pxs"/>
   								</c:when>  
  								<c:otherwise> 
  									<img  src="${pageContext.request.contextPath}\question.jpg" height="40px" width="50pxs"/>
   								</c:otherwise>  
							</c:choose>  
							<br/>
							<c:set var="surveyId" value="${surveys[i+j].id}" />
							<c:set var="surveyTitle" value="${surveys[i+j].title}" />
							<a href="${pageContext.request.contextPath}/EngageSurveyAction_entry?surveyId=${surveyId}">${surveyId} ${surveyTitle}</a>
						</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</body>
</html>