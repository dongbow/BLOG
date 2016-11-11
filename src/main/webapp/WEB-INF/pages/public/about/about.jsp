<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于我-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/about.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
</head>
<body>
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<div id="main">
	<h1 class="t_nav">
		<span>n像“草根”一样，紧贴着地面，低调的存在，冬去春来，枯荣无恙。 </span>
		<a href="${base}/index" class="n1">网站首页</a>
		<a href="${base}/about" class="n2">关于我</a>
	</h1>
	<div class="about left">
		<c:import url="${base}/about/left"></c:import>
	</div>
	<div class="right">
		<c:import url="${base}/about/right"></c:import>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>