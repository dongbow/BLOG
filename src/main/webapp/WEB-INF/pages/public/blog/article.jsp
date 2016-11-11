<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<%@include file="/commons/common.jsp" %>
<%@page import="cn.ifxcode.bean.Page" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${base}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blogTopic.title}-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/style_common.css?ver=${version}" type="text/css" rel="stylesheet">
<link href="${base}/static/css/blog.css?ver=${version}" type="text/css" rel="stylesheet">
<link href="${base}/static/css/publicbase.css?ver=${version}" type="text/css" rel="stylesheet">
<link href="${base}/static/css/article.css?ver=${version}" type="text/css" rel="stylesheet">
<link href="${base}/static/css/shCoreDefault.css?ver=${version}" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js?ver=${version}" type="text/javascript"></script>
<script src="${base}/static/js/shCore.js?ver=${version}" type="text/javascript"></script>
<script src="${base}/static/js/syntaxhighlighter.js?ver=${version}" type="text/javascript"></script>
<script type="text/javascript" src="${base}/plugins/ckeditor4/ckeditor.js"></script>
<script type="text/javascript" src="${base}/static/js/article.js"></script>
<link href="${base}/plugins/sweetalert/sweetalert.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${base}/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
SyntaxHighlighter.config.clipboardSwf = '${base}/plugins/clipboard.swf';
SyntaxHighlighter.all();
</script>
<script type="text/javascript">
$(function(){
	$('#replylogin').click(function(){
		var url=encodeURIComponent(window.location.href);
		$('#replylogin').attr('href','${base}/account/login?backurl='+url);
	});
});
</script>
</head>
<body>
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<div class="spage">
<div id="main">
	<h1 class="t_nav">
		<span>我们长路漫漫，只因学无止境。</span>
		<a href="${base}/index" class="n1">网站首页</a>
		<a href="${base}/blog" class="n2">学无止境</a>
	</h1>
	<div class="blogmenu left">
		<c:import url="${base}/blog/left"></c:import>
	</div>
	<div class="right">
		<div class="article_title">   
		    <c:if test="${blogTopic.createOrRepost == 0}">
				<span class="ico ico_type_Original"></span>
			</c:if>
			<c:if test="${blogTopic.createOrRepost == 1}">
				<span class="ico ico_type_Repost"></span>
			</c:if>
		    <h1>
		        <span class="link_title">
		        	<a href="${base}/blog/article/${blogTopic.bid}">
		        		${blogTopic.title}         
		        	</a>
		        </span>
		    </h1>
		</div>
		<div class="article_manage clearfix">
	        <div class="article_l">
	            <span class="link_categories">
	            	标签：
	            	<c:forEach items="${blogTopic.blogSigns}" var="BlogSign">
	            		<a target="_blank" href="${base}/blog/sign/${BlogSign.bsname}/article">${BlogSign.bsname}</a>
	            	</c:forEach>
	            </span>
	        </div>
	        <div class="article_r">
	            <span class="link_postdate">
	            	<fmt:formatDate value="${blogTopic.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	            </span>
	            <span title="阅读次数" class="link_view">${blogTopic.viewcount}人阅读</span>
	            <span title="评论次数" class="link_comments"> 
	            	<a href="${base}/blog/article/${blogTopic.bid}#comments">评论</a>(${blogTopic.replycount})
	            </span>
	        </div>
	    </div>
	    <div class="category clearfix">
	        <div class="category_l">
	           <img src="${base}/static/images/category_icon.jpg">
	            <span>分类：</span>
	        </div>
	        <div class="category_r">
                <label>
                    <a href="${base}/blog/classify/${blogTopic.blogClassify.cid}/article"><span>${blogTopic.blogClassify.name}</span></a>
                </label>                    
	        </div>
	    </div>
	    <div class="article_content" id="article_content">
			${blogTopic.content}
		</div>
		<div class="bdsharebuttonbox">
			<a href="#" class="bds_more" data-cmd="more"></a>
			<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
			<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
			<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
			<a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a>
			<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
		</div>
		<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"1","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
		<div articleid="${blogTopic.bid}" id="digg">
            <dl <c:if test="${user != null}">onclick="praise(${blogTopic.bid});"</c:if> class="digg digg_able" id="btnDigg">
                <dt class="able">顶</dt>
                <dd class="able">${blogTopic.praisecount}</dd>
            </dl>
            <dl  <c:if test="${user != null}">onclick="notpraise(${blogTopic.bid});"</c:if> class="digg digg_enable" id="btnBury">
                <dt class="enable">踩</dt>
                <dd class="enable">${blogTopic.notpraisecount}</dd>
            </dl>
        </div>
        <ul class="article_next_prev">
        	<c:if test="${minBlogTopic != null}">
        		<li class="prev_article">
        			<span>上一篇</span>
        			<a href="${base}/blog/article/${minBlogTopic.bid}" title="${minBlogTopic.title}">${minBlogTopic.title}</a>
        		</li>
        	</c:if>
            <c:if test="${maxBlogTopic != null}">
        		<li class="next_article">
        			<span>下一篇</span>
        			<a href="${base}/blog/article/${maxBlogTopic.bid}" title="${maxBlogTopic.title}">${maxBlogTopic.title}</a>
        		</li>
        	</c:if>
    	</ul>
        <div style="clear:both; height:10px;"></div>
        <div style="" class="similar_article">
            <h4>我的同类文章</h4>
            <div style="margin:20px 0px 0px 0px" class="similar_c">
                <div class="similar_c_t">
	                <label class="similar_cur">
	                    <span><a href="${base}/blog/classify/${blogTopic.blogClassify.cid}/article">${blogTopic.blogClassify.name}<em>（${blogTopic.blogClassify.blogcount}）</em></a></span>
	                </label>
                </div>
                <div style="max-height:195px;" data-mod="popu_141" class="similar_wrap tracking-ad">
                    <ul class="similar_list">
                    	<c:forEach items="${classifyBlogTopic}" var="BlogTopic">
                    		<li>
                    			<em>•</em>
                    			<a target="_blank" href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}">${BlogTopic.title}</a>
                    			<span><fmt:formatDate value="${blogTopic.createtime}" pattern="yyyy-MM-dd"/></span>
                    			<label><i>阅读</i><b>${BlogTopic.viewcount}</b></label>
                    		</li> 
                    	</c:forEach>
                    </ul>
            	</div>
        	</div>
		</div>
		<div class="comment_class">
		    <div class="panel_head" id="comment_title">
		        <span class="see_comment">查看评论</span>
		        <a name="comments"></a>
		    </div>
		    <c:forEach items="${blogReplyList}" var="BlogReply">
		    	<div id="comment_list">
			    	<dl id="comment_${BlogReply.bg_rid}" class="comment_item comment_topic">
			    		<dt class="comment_head">
				    		<span class="user">
				    			<a target="_blank" href="${base}/space/${BlogReply.user.nickname}" class="username">${BlogReply.user.nickname}</a>
				    			<span class="ptime">
				    				<fmt:formatDate value="${BlogReply.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				    				发表
				    			</span>  
				    			<a title="回复：${BlogReply.user.nickname}" class="cmt_btn reply" href="javascript:;"
				    				<c:if test="${user != null}">onclick="childReply(this,${BlogReply.bg_rid},${BlogReply.user.uid});"</c:if>>[回复]</a>
				    		</span>
			    		</dt>
			    		<dd class="comment_userface">
			    			<a target="_blank" href="${base}/space/${BlogReply.user.nickname}"><img width="40" height="40" src="${base}/${BlogReply.user.userimage}"></a>
			    		</dd>
			    		<dd class="comment_body">
			    			${BlogReply.content}
						</dd>
					</dl>
					<div class="clear"></div>
				</div>
		    </c:forEach>
		    <div id="comment_bar" style="display: none;"></div>
		    <c:if test="${user == null}">
			    <div id="comment_form">
			    	<div class="guest_link">
			    		您还没有登录,请
			    		<a id="replylogin" href="${base}/account/login">[登录]</a>
			    		或
			    		<a href="${base}/account/register">[注册]</a></div>
			    </div>
		    </c:if>
		    <c:if test="${user != null}">
		    	<div class="bookreply">
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
						<form id="replyform" action="${base}/blog/article/doreply" method="post">
							<input name="blog_id" type="hidden" value="${blogTopic.bid}">
							<textarea id="bookreplyeditor" name="content" rows="12" cols="93"></textarea>
							<button>发表</button>
						</form>
					</div>
				</div>
			</c:if>
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