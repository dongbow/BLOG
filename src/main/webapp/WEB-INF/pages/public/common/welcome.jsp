<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎回来-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<style type="text/css">
body{background: #f2f2f2;}
#footer{bottom:0;width: 100%;background: #fff;position: fixed;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	reload();
	time();
});
function reload(){
	var titlename = '系统提示';
	var context = '${user.nickname}，你已登录，欢迎回来<br/>'
				+'正在跳转回首页...<label class="time" style="color:#f40;">5</label><br/>'
				+'如果没有跳转，请点击这里'
				+'<a href="${base}/index" style="color:#19b4ea;">首页</a>';
	checktip(titlename,context);
	$('#checktip').show();
}
function time(){
	var time = $('.time').text();
	if(time>1){
		time--;
		$('.time').text(time);
	}else{
		window.location.href = '${base}/index';
	}
	setTimeout("time()", 1000);
}
</script>
</head>
<body>
	<c:import url="${base}/common/header">
		<c:param name="uid" value="${user.uid}"></c:param>
	</c:import>
	<jsp:include page="/WEB-INF/pages/public/common/tip.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>