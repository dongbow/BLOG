<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言板-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/book.css" type="text/css" rel="stylesheet">
<link href="${base}/plugins/sweetalert/sweetalert.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/plugins/ckeditor4/ckeditor.js"></script>
<script type="text/javascript" src="${base}/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.reply_p').mouseover(function(){
		$(this).find('.do').show().css("display","inline");
	});
	
	$('.reply_p').mouseout(function(){
		$(this).find('.do').hide();
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
		<span>你，生命中最重要的过客，之所以是过客，因为你未曾为我停留。</span>
		<a href="${base}/index" class="n1">网站首页</a>
		<a href="${base}/book" class="n2">留言板</a>
	</h1>
	<div class="book left">
		<div class="yanyu">
			<p>指缝溜走的，不尽是缺憾；</p>
			<p>岁月沉淀的，不都是圆满。</p>
		</div>
		<div class="booktop">
			<a>留言</a>
		</div>
		<div id="bookdetails">
			<c:if test="${user!=null}">
			    	<script type="text/javascript" src="${base}/static/js/book.js"></script>
			</c:if>
			<c:if test="${bookList == null}">
				暂无内容！
			</c:if>
			<c:if test="${bookList != null}">
				<c:forEach items="${bookList}" var="BookReply">
					<div id="parentReply_${BookReply.id}" class="reply_p">
						<div class="head">
							<a href="${base}/space/${BookReply.user.nickname}"><img alt="头像" width="50" src="${base}/${BookReply.user.userimage}"></a>
						</div>
						<div class="content">
							<div class="username">
								<a href="${base}/space/${BookReply.user.nickname}" style="color:#f66;">${BookReply.user.nickname}</a>
								<input type="hidden" class="uid" name="uid" value="${BookReply.user.uid}">
							</div>
							<p>
								${BookReply.content}
							</p>
							<div class="tool">
								<span title="<fmt:formatDate value="${BookReply.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
									<fmt:formatDate value="${BookReply.createtime}" pattern="yyyy-MM-dd HH:mm"/>
								</span>
								<div class="do"style="display: none;">
									<a id="reply_${BookReply.id}" class="replyclick" href="javascript:;">回复</a>
									<a id="tipoff_${BookReply.id}" class="tipoff" href="javascript:;">举报</a>
								</div>
							</div>
							<div id="childreply_${BookReply.id}" class="notclick">
								<div class="img">
									<img alt="头像" width="40" height="40" src="${base}/${user.userimage}">
								</div>
							</div>
							<c:if test="${ BookReply.childBookreplies != '[]' && BookReply.childBookreplies != null}">
								<div class="childcont">
									<c:forEach items="${BookReply.childBookreplies}" var="BookReply">
										<div class="cc">
											<div class="head">
												<a href="${base}/space/${BookReply.user.nickname}"><img alt="头像" width="40" src="${base}/${BookReply.user.userimage}"></a>
											</div>
											<div class="ccontent">
												<div class="username">
													<a href="${base}/space/${BookReply.user.nickname}" style="color:#f66;">${BookReply.user.nickname}</a>
													&nbsp;&nbsp;回复&nbsp;&nbsp;
													<a href="${base}/space/${BookReply.parentReply.user.nickname}" style="color:#f66;">${BookReply.parentReply.user.nickname}</a>
													<input type="hidden" class="uid" name="uid" value="${BookReply.user.uid}">
												</div>
												<p>
													${BookReply.content}
												</p>
											</div>
											<div class="tool">
												<span title="<fmt:formatDate value="${BookReply.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
													<fmt:formatDate value="${BookReply.createtime}" pattern="yyyy-MM-dd HH:mm"/>
												</span>
												<div class="do"style="display: none;">
													<a id="reply_${BookReply.id}" class="replyclick" href="javascript:;">回复</a>
													<a id="tipoff_${BookReply.id}" class="tipoff" href="javascript:;">举报</a>
												</div>
											</div>
											<div id="childreply_${BookReply.id}" class="notclick">
												<div class="img">
													<img alt="头像" width="40" height="40" src="${base}/${user.userimage}">
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:if>					
						</div>
					</div>
				</c:forEach>
 				<c:if test="${user==null}">
			    	<script type="text/javascript">
			        	$(function(){
			                $('.replyclick').click(function(){
			                	var url=encodeURIComponent(window.location.href);
			    				var titlename = '系统提示';
			    				var context = 
			    					'你还没有登录，没有权限回复<br/>'
			    					+'<a href="${base}/account/login?backurl='+url+'">登录 | </a>'
			    					+'<a href="${base}/account/register.htm">注册</a>';
			    					checktip(titlename,context);
			    					$('#checktip').show();
			                });
			                $('.tipoff').click(function(){
			                	var url=encodeURIComponent(window.location.href);
			    				var titlename = '系统提示';
			    				var context = 
			    					'你还没有登录，没有权限举报<br/>'
			    					+'<a href="${base}/account/login?backurl='+url+'">登录 | </a>'
			    					+'<a href="${base}/account/register.htm">注册</a>';
			    					checktip(titlename,context);
			    					$('#checktip').show();
			                });
						});
					</script>
					<jsp:include page="/WEB-INF/pages/public/common/tip.jsp"></jsp:include>
				</c:if> 
			</c:if>
			<div class="page">
				<tags:page page="${page}"></tags:page>
			</div>
		</div>
		<div class="bookreply">
		<c:if test="${user == null }">
			登录之后即可发表留言！没有账号？<a style="color:#f44;" href="${base}/account/register">注册</a>
		</c:if>
		<c:if test="${user != null }">
			<div class="img">
				<img alt="" width="50" height="50" src="${base}/${user.userimage}">
			</div>
			<script type="text/javascript">
				$(function () {
				    CKEDITOR.replace('bookreplyeditor', { 
				   		removePlugins: 'elementspath',
				   		resize_enabled: false, 
				   		toolbar: 'reply', 
				   		height: '100px', 
				   		width: '650px',
				   		toolbarLocation:'bottom'
				    });
				});
			</script>
			<div class="be">
				<form id="" action="${base}/book/doreply" method="post">
					<textarea id="bookreplyeditor" name="content" rows="12" cols="93"></textarea>
					<button class="replybtn">发表</button>
				</form>
			</div>
			</c:if>
		</div>
	</div>
	<div class="right">
		<c:import url="${base}/about/right"></c:import>
	</div>
</div>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
</body>
</html>