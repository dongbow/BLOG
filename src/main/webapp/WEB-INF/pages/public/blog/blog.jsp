<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" xmlns:wb="http://open.weibo.com/wb">
<html>
<head>
<base href="${base}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/blog.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
WB2.anyWhere(function(W){
    W.widget.followButton({
        'nick_name': 'Smart_咚咚',  //用户昵称
        'id': "wb_follow_btn",
        'show_head' : true, //是否显示头像
        'show_name' : true, //是否显示名称
        'show_cancel': true //是否显示取消关注按钮
    });
});
</script>
</head>
<body>
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<div id="main">
	<h1 class="t_nav">
		<span>我们长路漫漫，只因学无止境。</span>
		<a href="${base}/index" class="n1">网站首页</a>
		<a href="${base}/blog" class="n2">学无止境</a>
	</h1>
	<div class="blogmenu left">
		<c:import url="${base}/blog/left"></c:import>
	</div>
	<div class="rpage">
		<div class="right">
			<c:forEach items="${blogTopicList}" var="BlogTopic">
				<div id="actical_${BlogTopic.bid}" class="list_item">
					<div class="article_title">   
						<c:if test="${BlogTopic.createOrRepost == 0}">
							<span class="ico ico_type_Original"></span>
						</c:if>
						<c:if test="${BlogTopic.createOrRepost == 1}">
							<span class="ico ico_type_Repost"></span>
						</c:if>
					    <h1>
					        <span class="link_title">
						        <a href="${base}/blog/article/${BlogTopic.bid}">
						        	${BlogTopic.title}  
						        </a>
							</span>	
					    </h1>
					</div>
					<div class="article_description">
						${BlogTopic.description} 
					</div>
					<div class="article_manage">
						<span class="link_postdate">
							<fmt:formatDate value="${BlogTopic.createtime}" pattern="yyyy-MM-dd HH:mm"/>
						</span>
						<span title="阅读次数" class="link_view">
							<a title="阅读次数" href="${base}/blog/article/${BlogTopic.bid}">阅读</a>
							(${BlogTopic.viewcount})
						</span>
						<span title="评论次数" class="link_comments">
							<a title="评论次数" href="${base}/blog/article/${BlogTopic.bid}#comments">评论</a>
							(${BlogTopic.replycount})
						</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="page">
			<tags:page page="${page}"></tags:page>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>