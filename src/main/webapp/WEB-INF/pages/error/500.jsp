<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="${base}/"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<style>
	*{
		margin:0;padding:0;
	}
	
	body{
		background:#fff; 
	}
	
	.err_tip a{
		display:block;
		float:left;
		margin-right:20px;
		width:100px;
		height:32px;
		background:#F99;
		color:#fff;
		text-decoration:none;
		border:1px solid #f96;;
		text-align:center;
		font:16px/2em "microsoft yahei";
	}
	
	.err_tip p{
		color:#FFF;
		font:30px "microsoft yahei";
		margin-bottom:70px;
	}
	
	#notfound_center{
		width:100%;
		height:540px;
		background:#19b4ea;
	}
	
	.main_err{
		margin:0 auto; 
		width:1000px; 
		height:auto; 
		background-color:#19b4ea; 
		top:100px;
	}
	
	.err_back span{
		display: block;
		height: 540px;
		width: 560px;
		background:url("${base}/static/images/error_500.png") no-repeat;
	}
	
</style>
</head>
<body>
	<c:import url="${base}/common/header">
		<c:param name="uid" value="${user.uid}"></c:param>
	</c:import>
    <div id="notfound_center">
        <div class="main_err">
        	<div class="err_back" style="float:left;"><span></span></div>
            <div class="err_tip" style="float:right; margin-right:120px; margin-top:240px">
                <p>系统内部出了点问题</p>
                <p>请稍后再试</p>
                <a href="${base}/index">返回首页</a>
                <a href="javascript:;" onclick="javascript:history.go(-1);">返回上一页</a>
            </div>
        </div>
    </div>
    <jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>