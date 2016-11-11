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
<form id="addUserAction" action="" method="post">
	<div class="easyui-panel" style="width:350px;padding:20px">
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>登录名:</div>
			<input name="username" class="easyui-textbox" style="width:90%;height:22px">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>昵称:</div>
			<input name="nickname" class="easyui-textbox" style="width:90%;height:22px">
		</div>
			<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>密码:</div>
			<input name="password" class="easyui-textbox" style="width:90%;height:22px"">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>邮箱:</div>
			<input class="easyui-textbox" data-options="validType:'email'" style="width:90%;height:22px">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>性别:</div>
			<input class="easyui-combobox" 
				name="sex"
				data-options="
						url:'${base}/json/sex.json',
						method:'get',
						valueField:'id',
						textField:'value',
						multiple:true,
						panelHeight:'auto'
			">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>出生:</div>
			<input name="birth" class="easyui-textbox" style="width:90%;height:22px" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>省份:</div>
			<input name="province" class="easyui-textbox" style="width:90%;height:22px">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>城市:</div>
			<input name="city" class="easyui-textbox" style="width:90%;height:22px">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>QQ:</div>
			<input name="qq" class="easyui-textbox" style="width:90%;height:22px">
		</div>
		<div style="margin-bottom:8px;width: 50%;float: left;">
			<div>状态:</div>
			<input class="easyui-combobox" 
				name="status"
				data-options="
						url:'${base}/json/status.json',
						method:'get',
						valueField:'id',
						textField:'value',
						multiple:true,
						panelHeight:'auto'
			">
		</div>
		<div style="margin-bottom:20px;width: 50%;float: left;">
			<div>用户组:</div>
			<input class="easyui-combobox" 
				name="rid"
				data-options="
					url:'${path}/system/admin/rolenamelist',
					method:'post',
					valueField:'rid',
					textField:'name',
					multiple:true,
					panelHeight:'auto'
			">
		</div>
	</div>
</form>
</body>
</html>