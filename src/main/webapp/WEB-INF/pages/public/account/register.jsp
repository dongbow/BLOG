<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${base}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册-一个程序员的个人博客网站</title>
<%@ include file="/commons/basejs.jsp" %> 
<link rel="shortcut icon" href="${staticPath }/static/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${staticPath}/static/css/register.css">
<script type="text/javascript" src="${base}/static/js/validate.js"></script>
</head>
<body>
<div class="top_div"></div>
<div class="bottom_div">
	<div class="logo">
		<a href="${base}/index"><img alt="LOGO" src="${base}/static/images/logo.png"></a>
	</div>
	<form method="post" id="reform" action="${path}/account/doregister">
		<div class="error">
			<span class="errorcontent"></span>
		</div>
		<table>
			<tbody>
				<tr>
					<td class="name">登录名：</td>
					<td><input id="username" class="ipt" type="text" name="username" placeholder="英文、数字,3-20位,唯一" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="name">昵称：</td>
					<td><input id="nickname" class="ipt" type="text" name="nickname" placeholder="3-20位，唯一，中文、英文、数字、标点_" maxlength="20"/></td>
				</tr>
				<tr>
					<td class="name">密码：</td>
					<td><input id="password" class="ipt" type="password" name="password" placeholder="请输入密码,6-16位" maxlength="16"/></td>
				</tr>
				<tr>
					<td class="name">确认密码：</td>
					<td><input id="rpassword" class="ipt" type="password" name="rpassword" placeholder="请再次输入密码，6-16位" maxlength="16"/></td>
				</tr>
				<tr>
					<td class="name">邮箱：</td>
					<td><input id="mail" class="ipt " type="text" name="email" placeholder="请输入有效的邮箱地址"/></td>
				</tr>
				<tr>
					<td class="name">验证码：</td>
					<td>
						<input id="validatacode" class="ipt code" type="text" name="verifycode" placeholder="请输入验证码" maxlength="4"/>
						<a class="verifycode" href="javascript:;"><img alt="验证码" title="点击更换验证码" src="${base}/validateCode.img"></a>
					</td>
				</tr>
			</tbody>
		</table>
        <div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(231, 231, 231); border-top-width: 1px; border-top-style: solid;">
            <P style="margin: 0px 35px 20px 45px;">
                <span style="float: left;">
                	<a style="color: #333;" href="${path}/account/login">返回登录</a>
                </span>
                <span style="float: none;">
                    <a style="background: rgb(0, 142, 173); padding: 7px 36px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" href="javascript:;" onclick="submitForm()">注册</a>
                </span>
            </P>
        </div>
    </form>
</div>
</body>
</html>