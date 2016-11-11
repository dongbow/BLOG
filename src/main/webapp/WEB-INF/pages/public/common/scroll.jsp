<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/commons/global.jsp" %>
<style>
.scroll{
	position: fixed;
	float: right;
	margin-top:400px;
    left: 51%;
    margin-left: 510px;
    z-index: 1000;
}
.scroll ul{
	margin: 0;
	width: 60px;
	height: 60px;
}
.scroll li{
	display: block;
	width: 60px;
	height: 60px;
}
.scroll a{
	background: url("${base}/static/images/scroll.png") no-repeat scroll 0px 0px transparent;
	width: 60px;
	height: 60px;
	display: block;
}
.scroll li.scrolltop a{
	background-position: 0px 0px;
}
.scroll li.scrolltop a:hover{
	background: url("${base}/static/images/scroll.png") repeat 60px 60px scroll;
}
</style>
<script type="text/javascript">
$(function(){
	$(window).scroll(function(){
	    if ($(window).scrollTop()>100){
	        $(".scrolltop a").fadeIn(1500);
	    }else{
	        $(".scrolltop a").fadeOut(1500);
	    }
	});

	$(".scrolltop a").click(function(){
	    $('body,html').animate({scrollTop:0},500);
	    return false;
	});
});
</script>
<div class="scroll">
	<ul>
		<li class="scrolltop"><a href="javascript:;" title="返回顶部" style="display: none"></a></li>
	</ul>
</div>