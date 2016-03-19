<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/mytaglib" prefix="zz"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理角色</title>
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
	<zz:form  action="${pageContext.request.contextPath}/RoleAction_saveOrUpdateRole">
	
		<input type="hidden" name="id"  value="${model.id}">	<br/>
		角色名称:<input type="text" name="roleName"  value="${model.roleName}">	<br/>
		角色值:<input type="text" name="roleValue"  value="${model.roleValue}">	<br/>
		角色描述:<input type="text" name="roleDesc"  value="${model.roleDesc}">	<br/>

		<select name="noOwnRight"  id="noOwnRight" size="8" style='width:500;' multiple="multiple" ondblclick="moveOption(document.getElementById('noOwnRight'), document.getElementById('ownRight'))">
			<c:forEach var="noOwnRight"  items="${noOwnRights}"  varStatus="status">
				<option value="${noOwnRight.id}">${noOwnRight.rightName}</option>
			</c:forEach>
		</select>
		
		<input name="sure1" type="button" id="sure1"
  onClick="moveOption(document.getElementById('noOwnRight'), document.getElementById('ownRight'));" value=">>" >
  
  		<input name="sure2" type="button" id="sure2"
  onClick="moveOption(document.getElementById('ownRight'), document.getElementById('noOwnRight'));" value="<<">
  
		<c:set var="ownRights" value="${model.rights}"/>
		<select name="ownRight"  id="ownRight" size="8" style='width:500;' multiple="multiple" ondblclick="moveOption(document.getElementById('ownRight'), document.getElementById('noOwnRight'))">
			<c:forEach var="ownRight"  items="${ownRights}"  varStatus="status">
				<option value="${ownRight.id}">${ownRight.rightName}</option>
			</c:forEach>
		</select>
		
		<zz:input type="submit" value="提交" />
	</zz:form>

</body>
</html>

