<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加权限</title>
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
	<form action="${pageContext.request.contextPath}/RightAction_saveOrUpdateRight" method="post">
		<input type="hidden"  name="id"  value="${model.id}"/> 	<br/>
		权限名称：<input type="text"  name="rightName"  value="${model.rightName}"/> 		<br/>
		URL：<input type="text"  name="rightUrl"  value="${model.rightUrl}"/> 						<br/>
		权限描述：<input type="text"  name="rightDesc"  value="${model.rightDesc}"/> 			<br/>
		权限码：<input type="text"  name="rightCode"  value="${model.rightCode}"/> 			<br/>
		权限位：<input type="text"  name="rightPos"  value="${model.rightPos}"/> 				<br/>
		是否公共：<input type="checkbox"  name="common"  id="common"  value="${model.common}"  onclick=changevalue(this.name)  ${model.common?"checked":""}/> 				<br/>
		<input type="submit"  value="提交"/>
	</form>	
</body>
</html>