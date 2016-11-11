<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<div class="panel myinfo">
	<div class="title">
		<span>个人信息</span>
	</div>
	<div class="panel_body">
		<div class="userimg">
			<div class="border">
				<a href="${base}/space/${superAdminInfo.nickname}" target="_blank">
					<img alt="头像" width="120" height="120" src="${base}/${superAdminInfo.userimage}">
				</a>
			</div>
			<a class="username" href="${base}/space/${superAdminInfo.nickname}" target="_blank"><span>${superAdminInfo.nickname}</span></a>
		</div>
		<ul class="info">
			<li class="bottom">订阅：<a class="rss" href="" target="_blank"><span><img src="${base}/static/images/ico_rss.gif">订阅</span></a></li>
			<li class="bottom">现居：${superAdminInfo.province}-${superAdminInfo.city}</li>
			<li class="bottom">微博：<a href="http://weibo.com/dongbow" target="_blank">Smart_咚咚</a>
				<wb:follow-button uid="3903589559" type="red_1" width="67" height="24" ></wb:follow-button>
			</li>
			<li>QQ&nbsp：${superAdminInfo.qq}</li>
		</ul>
		<ul class="count">
			<li class="bottom">博文：${superAdminInfo.blogcount}&nbsp篇</li>
			<li>评论：${superAdminInfo.replycount}&nbsp条</li>
		</ul>
	</div>
</div>
<div class="panel classify">
	<div class="title">
		<span>文章分类</span>
	</div>
	<div class="panel_body">
		<ul class="article">
			<c:forEach items="${blogClassifies}" var="BlogClassify">
				<li class="bottom">
					<a target="_blank" href="${base}/blog/classify/${BlogClassify.cid}/article">${BlogClassify.name}<span>(${BlogClassify.blogcount})</span></a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="panel archive">
	<div class="title">
		<span>时间归档</span>
	</div>
	<div class="panel_body">
		<ul class="article">
			<c:forEach items="${blogTopicTimes}" var="blogTopicTime">
				<li class="bottom">
					<a target="_blank" href="${base}/blog/date/${blogTopicTime.timedetail}/article">${blogTopicTime.timeclassify}<span>(${blogTopicTime.blogcount})</span></a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="panel hotview">
	<div class="title">
		<span>浏览排行</span>
	</div>
	<div class="panel_body">
		<ul class="article">
			<c:forEach items="${topicViewCountTop8}" var="BlogTopic">
				<li class="bottom">
					<a class="detail" href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a>
					<span class="number" title="浏览数">(${BlogTopic.viewcount})</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="panel hotreply">
	<div class="title">
		<span>评论排行</span>
	</div>
	<div class="panel_body">
		<ul class="article">
			<c:forEach items="${topicReplyCountTop8}" var="BlogTopic">
				<li class="bottom">
					<a class="detail" href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a>
					<span class="number" title="评论数">(${BlogTopic.replycount})</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="panel hotview">
	<div class="title">
		<span>最新文章</span>
	</div>
	<div class="panel_body">
		<ul class="article">
			<c:forEach items="${topiclastest5}" var="BlogTopic">
				<li class="bottom">
					<a class="detail" href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a>
					<span class="number" title="浏览数">(${BlogTopic.viewcount})</span>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>