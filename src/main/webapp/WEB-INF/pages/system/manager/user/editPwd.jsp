<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="editUserPwdForm" action="system/admin/user/editPwd" method="post">
	<div class="easyui-panel" style="width:292px;padding:20px">
		<div style="margin-bottom:8px;width: 100%;">
			<div>新密码:</div>
			<input type="password" name="password" class="easyui-textbox" style="width:90%;height:32px">
		</div>
		<div style="margin-bottom:8px;width: 100%;">
			<div>确认密码:</div>
			<input type="password" name="rpassword" class="easyui-textbox" style="width:90%;height:32px">
		</div>
	</div>
</form>
</body>
</html>