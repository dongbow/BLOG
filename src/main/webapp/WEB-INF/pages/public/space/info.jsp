<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${base}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${userinfo.nickname}的个人空间-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/space.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${base}/static/js/space.js" type="text/javascript"></script>
</head>
<body>
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<div class="main">
	<div class="headpanel">
	<div class="userimage">
		<c:if test="${userinfo.uid == user.uid || user.role.rid == 1}">
			<a class="top" title="点击修改头像" href="${base}/account/${userinfo.uid}/update?type=image">
				<img alt="头像" src="${base}/${userinfo.userimage}" width="120" height="120">
				<div class="showtip">
					<label>修改头像</label>
				</div>
			</a>
		</c:if>
		<c:if test="${userinfo.uid != user.uid && user.role.rid != 1}">
			<img alt="头像" src="${base}/${userinfo.userimage}" width="120" height="120">
		</c:if>
	</div>
	<span>${userinfo.nickname}</span>
	<div class="data">
		<div class="count">
			<a href="${base}/space/${userinfo.uid}/reply">评论：${userinfo.replycount}</a>
			<a>
				上次登录：<fmt:formatDate value="${userinfo.lastloginTime}" pattern="yyyy-MM-dd HH:mm"/>
			</a>
		</div>
	<c:if test="${user != null}">
		<div class="session">
			<c:if test="${user.role.rid == 1}">
				<a href="${base}/system/admin/index" target="_blank">进入后台</a>
			</c:if>
			<c:if test="${userinfo.uid == user.uid || user.role.rid == 1}">
				<a href="${base}/account/${userinfo.uid}/update?type=info">修改资料</a>
			</c:if>
			</div>
		</c:if>
	</div>		
</div>
	<div class="maininfo">
		<div class="leftmenu">
			<ul>
				<li class="hover">
					<a href="${base}/space/${userinfo.nickname}">
						<c:if test="${user != null && userinfo.uid == user.uid}">个人</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>资料
					</a>
				</li>
				<c:if test="${(user != null && userinfo.uid == user.uid) || user.role.rid == 1}">
					<li>
						<a href="${base}/space/${userinfo.uid}/message">
							<c:if test="${user != null && userinfo.uid == user.uid}">我的</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>消息
						</a>
					</li>
				</c:if>
				<li>
					<a href="${base}/space/${userinfo.uid}/reply">
						<c:if test="${user != null && userinfo.uid == user.uid}">我的</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>评论
					</a>
				</li>
				<li>
					<a href="${base}/space/${userinfo.uid}/book">
						<c:if test="${user != null && userinfo.uid == user.uid}">我的</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>留言
					</a>
				</li>
			</ul>
		</div>
		<div class="rightcont">
			<c:if test="${(user !=null && user.uid == userinfo.uid) || user.role.rid == 1}">
			<div class="body_info">
				<div class="head_title">
					<a>隐私资料</a>
				</div>
				<table>
					<tbody>
						<tr>
							<th>登录名：</th>
							<td>${userinfo.username}</td>
						</tr>
						<tr>
							<th>邮箱：</th>
							<td>${userinfo.email}</td>
						</tr>
						<tr>
							<th>上次登录时间：</th>
							<td><fmt:formatDate value="${userinfo.lastloginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>上次登录IP：</th>
							<td>${userinfo.lastloginip}</td>
						</tr>
						<tr>
							<th>注册IP：</th>
							<td>${userinfo.createip}</td>
						</tr>
					</tbody>
				</table>
			</div>
			</c:if>
			<div class="body_info">
				<div class="head_title">
					<a><c:if test="${user != null && userinfo.uid == user.uid}">我的</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>资料</a>
				</div>
				<table>
					<tbody>
						<tr>
							<th>ID：</th>
							<td>${userinfo.uid}</td>
						</tr>
						<tr>
							<th>昵称：</th>
							<td>${userinfo.nickname}</td>
						</tr>
						<tr>
							<th>性别：</th>
							<td>${userinfo.sex}</td>
						</tr>
						<tr>
							<th>生日：</th>
							<td><fmt:formatDate value="${userinfo.birth}" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<th>城市：</th>
							<td>${userinfo.province}-${userinfo.city}</td>
						</tr>
						<tr>
							<th>QQ：</th>
							<td>${userinfo.qq}</td>
						</tr>
						<tr>
							<th>状态：</th>
							<td>${userinfo.status}</td>
						</tr>
						<tr>
							<th>注册时间：</th>
							<td><fmt:formatDate value="${userinfo.createtime}" pattern="yyyy-MM-dd HH:mm"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>