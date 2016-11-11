<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/global.jsp" %>
<img width="150" height="150" style="margin-bottom: 20px;" src="${base}/${superAdminInfo.userimage}"/>
<p>网名：<span>${superAdminInfo.nickname}</span></p>
<p>现居：${superAdminInfo.province}&mdash;${superAdminInfo.city}</p>
<p>职业：马上加入程序猿大军</p>
<p>QQ：${superAdminInfo.qq}</p>
<p><a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=k_Tk5L2npaOhqqSjoaXT4uK98Pz_" style="text-decoration:none;">
	<img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_22.png"/>
</a></p>