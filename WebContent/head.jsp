<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<body>
	 <h1>信息收集系统</h1>
	 <a href="${pageContext.request.contextPath}/index.jsp">主页</a>	
	 <a href="${pageContext.request.contextPath}/SurveyAction_mySurveys">我的调查</a>		
	 <a href="${pageContext.request.contextPath}/SurveyAction_mySurveysForPage?curPage=1&pageSize=10&nextForward=0">我的调查分页</a>		
	 <a href="${pageContext.request.contextPath}/SurveyAction_newSurvey">新建调查</a>
	 <a href="${pageContext.request.contextPath}/EngageSurveyAction_toAvailableSurveyPage">参与调查</a>
	 <a href="${pageContext.request.contextPath}/RightAction_findAllRights">权限管理</a>
	 <a href="${pageContext.request.contextPath}/RoleAction_findAllRoles">角色管理</a>
	 <a href="${pageContext.request.contextPath}/UserAuthorizeAction_findAllUsers">用户授权管理</a>
	 <br/>
	 <s:debug></s:debug>
</body>
</html>