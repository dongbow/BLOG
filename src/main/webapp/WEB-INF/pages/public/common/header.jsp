<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user != null}">
<script type="text/javascript">
$(document).ready(message());

function message(){
	$.ajax({
		url : 'message/ajax',
		type : "POST",
		dataType : 'text',
		contentType : "application/json",
		success : function (count){
			if(count != 0){
				$('#message span').css('background','#f00');
				$('#msgtip').html(
						'<a href="<%=basePath %>space/${user.uid}/message">'
						+'<span>你有'+count+'条新消息，点击查看</span></a>');
				$('#header_panel').css("left",'74%');
				$('#header_panel').css('width','170px');
				$('#msgtip').show();
			}else{
				$('#message span').css('background','#09f');
				$('#msgtip').html('');
				$('#header_panel').css("left",'79%');
				$('#header_panel').css('width','76px');
				$('#msgtip').hide();
			}
			$('#m_count').text(count);
		},
		error: function () { 
			$('#message span').css('background','#09f');
			$('#msgtip').html('');
			$('#header_panel').css("left",'79%');
			$('#header_panel').css('width','76px');
			$('#msgtip').hide();
			$('#m_count').text('0');
		},
		async: true
	});
	setTimeout("message()", 1000*60*2);
}
</script>
</c:if>
<script type="text/javascript">
$(document).ready(function(){
	var url=encodeURIComponent(window.location.href);
	$('#exit').attr('href','<%=basePath%>account/logout?backurl='+url);
});

$(function(){
	$('#name_cont').mouseover(function(){
		$('#header_panel').show();
	});
	
	$('#name_cont').mouseout(function(){
		if($('#header_panel').mouseover){
			$('#header_panel').show();
		}else{
			$('#header_panel').delay(6000).fadeOut(1000).hide(0);
		}
	});
	
	$('#header_panel').mouseover(function(){
		$('#header_panel').show();
	});
	
	$('#header_panel').mouseout(function(){
		$('#header_panel').hide();//.delay(2000).fadeOut(1000).hide(0);
	});
	
	$('#login').click(function(){
		var url=encodeURIComponent(window.location.href);
		$('#login').attr('href','<%=basePath%>account/login?backurl='+url);
	});
});
</script>
<div id="hd">
  <div class="wp">
  <c:if test="${user==null}">
  	<div id="header_cont">
		<a id="logo" href="<%=basePath%>index"><img src="<%=basePath%>static/images/logo.png"></a>
		<a id="register" href="<%=basePath%>account/register">注册</a> 
		<p id="cow">|</p>
		<a id="login" href="<%=basePath%>account/login">登录</a>
	</div>
  </c:if>
  <c:if test="${user!=null}">
  <div id="header_cont">
      <a id="logo" href="<%=basePath%>index"> <img alt="LOGO" src="<%=basePath %>static/images/logo.png"> </a>
      <div id="name_cont">
          <a href="space/${user.uid}/message" target="_blank" id="message"><span id="m_count">0</span></a>
          <a id="usercenter" title="${dynamicUser.nickname}" target="_blank" href="space/${dynamicUser.nickname}">${dynamicUser.nickname}</a>
          <a id="userhead" href="space/${dynamicUser.nickname}" target="_blank">
          	<img id="cut" title="访问我的空间" src="<%=basePath %>${dynamicUser.userimage}"/>
          </a>
      </div>
	</div>
    <div id="header_panel" class="header_panel" style="display:none;">
    	<div class="list_item_trangle"></div>
    	<div id="msgtip" style="display:none;">
    	</div>
        <div class="bbs_exit"><a id="exit">退出</a></div>
    </div>
  </c:if>
    <div id="header_nav">
      <ul>
        <li class="index"><a href="<%=basePath%>index">首页</a></li>
      	<c:forEach items="${navigationList}" var="Navigation">
      		<li><a href="<%=basePath%>${Navigation.url}">${Navigation.name}</a></li>
      	</c:forEach>
      </ul>
    </div>
    <form action="search" method="get" target="_blank" id="searchform">
        <div id="header_search">
          <button id="scbar_btn" class="header_submit" type="submit"> 
            <strong>搜索</strong> 
          </button>
          <input id="scbar_txt" class="header_search" type="text" value="" name="keyword" placeholder="请输入关键字搜索" />
        </div>
    </form>
  </div>
</div>