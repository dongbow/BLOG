<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<div class="headpanel">
	<div class="userimage">
		<c:if test="${userinfo.uid == user.uid || user.role.rid == 1}">
			<a title="点击修改头像" href="${base}/account/${user.uid}/update?type=image">
				<img alt="头像" src="${base}/${userinfo.userimage}" width="120" height="120">
			</a>
		</c:if>
		<c:if test="${userinfo.uid != user.uid && user.role.rid != 1}">
			<img alt="头像" src="${base}/${userinfo.userimage}" width="120" height="120">
		</c:if>
	</div>
	<span>${userinfo.nickname}</span>
	<div class="data">
		<div class="count">
			<a href="">评论：${userinfo.replycount}</a>
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