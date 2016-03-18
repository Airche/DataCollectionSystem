<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改授权</title>
</head>
<SCRIPT LANGUAGE="JavaScript"> 
function moveOption(obj1, obj2)  
{  
     for(var i = obj1.options.length - 1 ; i >= 0 ; i--)  
     {  
         if(obj1.options[i].selected)  
         {  
            var opt = new Option(obj1.options[i].text,obj1.options[i].value);  
            opt.selected = true;  
            obj2.options.add(opt);  
            obj1.remove(i);  
        }  
     }  
} 
</SCRIPT>
<body>

	<jsp:include page="head.jsp" />	<br/>
	<form action="${pageContext.request.contextPath}/UserAuthorizeAction_saveOrUpdateUserAuthorize" method="post">
		<input type="text"  name="userId" value="${model.id}"><br>
		邮件：<input type="text"  value="${model.email}"><br>
		昵称：<input type="text"  value="${model.nickName}"><br>
		<c:set value="${model.roles}" var="roles"/>
		<select name="ownRoles"  id="ownRoles"  size="8" style='width:500;' multiple="multiple" ondblclick="moveOption(document.getElementById('ownRoles'), document.getElementById('noOwnRoles'))">
			<c:forEach items="${roles}" var="role" varStatus="status">
				<option value="${role.id}">${role.roleName}</option>
			</c:forEach>
		</select>
		<input name="sure1" type="button" id="sure1"
  onClick="moveOption(document.getElementById('ownRoles'), document.getElementById('noOwnRoles'));" value=">>" >
  
  		<input name="sure2" type="button" id="sure2"
  onClick="moveOption(document.getElementById('noOwnRoles'), document.getElementById('ownRoles'));" value="<<">
  
		<select name="noOwnRoles"  id="noOwnRoles" size="8" style='width:500;' multiple="multiple" ondblclick="moveOption(document.getElementById('noOwnRoles'), document.getElementById('ownRoles'))">
			<c:forEach items="${noOwnRolesList}" var="role" varStatus="status">
				<option value="${role.id}">${role.roleName}</option>
			</c:forEach>
		</select>
		<input type="submit" value="确认">
	</form>
</body>
</html>