<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>碎言碎语-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/mood.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<script src="${base}/static/js/modernizr.js"></script>
<![endif]-->
</head>
<body>
	<c:import url="${base}/common/header">
		<c:param name="uid" value="${user.uid}"></c:param>
	</c:import>
	<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
	<div class="moodlist">
		<h1 class="t_nav">
			<span>删删写写，回回忆忆，虽无法行云流水，却也可碎言碎语。</span>
			<a href="${base}/index" class="n1">网站首页</a>
			<a href="${base}/mood" class="n2">碎言碎语</a>
		</h1>
		<div class="bloglist">
			<c:forEach items="${moodList}" var="Mood">
				<ul class="arrow_box">
					<div class="sy">
						<p>${Mood.content}</p>
					</div>
					<span class="dateview"><fmt:formatDate value="${Mood.createtime}" pattern="yyyy-MM-dd"/></span>
				</ul>
			</c:forEach>
		</div>
		<div class="page">
			<tags:page page="${page}"></tags:page>
		</div>
	</div>
	<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
	<script src="${base}/static/js/silder.js"></script>
</body>
</html>