<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#header_nav').remove();
	$('#searchform').remove();
	$('#hd').css('height','80px');
});
</script>
<style type="text/css">
input{width:600px;height:30px;border:1px solid #aaa;padding:5px 10px;}
button{border:1px solid #aaa;background:#aaa;color:#fff;width:70px;height:40px;cursor: pointer;}
.font a,.font p{font:16px "microsoft yahei";color:#333}
.font a{color:#000;font-weight: bold;}
.font a:hover{color:#19b4ea}
.font p{margin-top: 5px;margin-bottom: 10px}
</style>
</head>
<body style="background:#f2f2f2;margin: 0;padding: 0">
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<div style="margin:0 auto;width:950px;padding: 20px 50px 20px 0;">
	<form action="search">
		<input name="keyword" type="text" value="${param.keyword}">
		<button>搜索</button>
	</form>
</div>
<div style="margin:0 auto;width:960px;height:auto;background: #fff;min-height: 1000px;padding: 20px;" class="font">
<c:if test="${blogTopics == null || blogTopics eq '[]'}">
	<p>无查询结果</p>
</c:if>
<c:if test="${blogTopics != null}">
	<c:forEach items="${blogTopics}" var="BlogTopic">
		<a href="blog/article/${BlogTopic.bid}" target="_blank" title="${BlogTopic.title}">
			${BlogTopic.title}
		</a>
		<p>${BlogTopic.content}</p>
	</c:forEach>
</c:if>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>