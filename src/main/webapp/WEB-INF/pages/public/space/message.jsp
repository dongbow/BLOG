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
<title>${userinfo.nickname}的消息-一个程序员的个人博客网站</title>
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
				<a title="点击修改头像" href="${base}/account/${user.uid}/update?type=image">
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
						<a href="${base}/account/${user.uid}/update?type=info">修改资料</a>
					</c:if>
				</div>
			</c:if>
		</div>		
	</div>
	<div class="maininfo">
		<div class="leftmenu">
			<ul>
				<li>
					<a href="${base}/space/${userinfo.nickname}">
						<c:if test="${user != null && userinfo.uid == user.uid}">个人</c:if><c:if test="${userinfo.uid != user.uid}">他的</c:if>资料
					</a>
				</li>
				<c:if test="${(user != null && userinfo.uid == user.uid) || user.role.rid == 1}">
					<li class="hover">
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
			<ul class="msgClass">
			  <li <c:if test="${param.msg eq null || param.msg eq 'unread'}">class="msgOver"</c:if>><a href="${base}/space/${userinfo.uid}/message?msg=unread">未读</a></li>
			  <li <c:if test="${param.msg eq 'read'}">class="msgOver"</c:if>><a href="${base}/space/${userinfo.uid}/message?msg=read">已读</a></li>
			  <li <c:if test="${param.msg eq 'delete'}">class="msgOver"</c:if>><a href="${base}/space/${userinfo.uid}/message?msg=delete">已删除</a></li>
			</ul>
			<table id="message">
				<thead>
					<tr>
						<th class="mchoose">选择</th>
						<th class="status">状态</th>
						<th class="mcontent">内容</th>
						<th class="msend">发送人</th>
						<th width="mtime">发送时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${messageList}" var="MessageVo">
						<tr>
							<td class="mchoose "><input type="checkbox" name="msgid" value="${MessageVo.messageid}" class="one"></td>
							<td class="status <c:if test="${1 eq MessageVo.status}">blod</c:if>">
								[<c:if test="${'1' eq MessageVo.status}">未读</c:if>
								 <c:if test="${'2' eq MessageVo.status}">已读</c:if>
								 <c:if test="${'3' eq MessageVo.status}">已删除</c:if>
								]
							</td>
							<td class="mcontent <c:if test="${1 eq MessageVo.status}">blod</c:if>"><a href="">${MessageVo.message}</a></td>
							<td class="msend <c:if test="${1 eq MessageVo.status}">blod</c:if>"><a href="${base}/space/${MessageVo.send_nickname}">${MessageVo.send_nickname}</a></td>
							<td width="mtime <c:if test="${1 eq MessageVo.status}">blod</c:if>"><fmt:formatDate value="${MessageVo.sendtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="toolbar">
				<span><input type="checkbox" name="msgid" value="0" class="allmessage"></span>
				<c:if test="${param.msg eq null || param.msg eq 'unread'}"><a href="">标记为已读</a></c:if>
				<c:if test="${param.msg eq null || param.msg eq 'unread' || param.msg eq 'read'}"><a href="">删除</a></c:if>
				<c:if test="${param.msg eq 'delete'}"><a href="">恢复为已读</a></c:if>
			</div>
			<c:if test="${page.totalPage != 0}">
				<div class="page">
					<tags:page page="${page}"></tags:page>
				</div>
			</c:if>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>