<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/basejs.jsp" %>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:true,title:'我的资料'" >
	    <form id="infoForm" method="post" action="${base}/user/updateMyInfo">
	        <table>
	        	<tbody>
	        		<tr>
	        			<th>头像：</th>
	        			<td>
	        				<a href="javascript:;"><img width="100" height="100" src="${base}/${myinfo.userimage}"></a>
	        				<a href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改头像</a>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>昵称：</th>
	        			<td><input type="text" name="nickname" value="${myinfo.nickname}"></td>
	        		</tr>
	        		<tr>
	        			<th>邮箱：</th>
	        			<td><input type="text" name="email" value="${myinfo.email}"></td>
	        		</tr>
	        		<tr>
	        			<th>性别：</th>
	        			<td>
	        				<select name="sex">
	        					<option value="0" <c:if test="${0 eq myinfo.sex}">selected</c:if>>男</option>
	        					<option value="1" <c:if test="${1 eq myinfo.sex}">selected</c:if>>女</option>
	        				</select>
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>生日：</th>
	        			<td><input type="text" name="birth" 
	        				value="<fmt:formatDate value="${myinfo.birth}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"></td>
	        		</tr>
	        		<tr>
	        			<th>省份：</th>
	        			<td>
	        				<input type="text" name="province" value="${myinfo.province}">
	        				<input type="text" name="city" value="${myinfo.city}">
	        			</td>
	        		</tr>
	        		<tr>
	        			<th>QQ：</th>
	        			<td><input type="text" name="qq" value="${myinfo.qq}"></td>
	        		</tr>
	        	</tbody>
	        </table>
	        <button class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</button>
	    </form>
    </div>
</body>
</html>