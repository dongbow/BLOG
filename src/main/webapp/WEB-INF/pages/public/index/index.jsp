<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱分享-一个程序员的个人博客网站</title>
<link rel="shortcut icon" href="${base}/static/images/favicon.ico" />
<link href="${base}/static/css/base.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/index.css" type="text/css" rel="stylesheet">
<link href="${base}/static/css/style_common.css" type="text/css" rel="stylesheet">
<script src="${base}/static/js/jquery/jquery-1.8.0.min.js" type="text/javascript"></script>
</head>
<body>
<c:import url="${base}/common/header">
	<c:param name="uid" value="${user.uid}"></c:param>
</c:import>
<jsp:include page="/WEB-INF/pages/public/common/scroll.jsp"></jsp:include>
<article>
  <h2 class="title_tj">
    <p>文章<span>推荐</span></p>
  </h2>
  <div class="bloglist left">
	  <c:forEach items="${topics}" var="BlogTopic">
		  <div id="article_${BlogTopic.bid}">
			   <h3><a class="hover" title="${BlogTopic.title}" target="_blank" href="${base}/blog/article/${BlogTopic.bid}">${BlogTopic.title}</a></h3>
			   <ul>
			       <p>${BlogTopic.description}</p>
			       <a title="点击阅读全文" href="${base}/blog/article/${BlogTopic.bid}" target="_blank" class="readmore">阅读全文>></a>
			   </ul>
			   <p class="dateview">
			       <span><fmt:formatDate value="${BlogTopic.createtime}" pattern="yyyy-MM-dd"/></span>
			       <span>个人博客：[<a href="${base}/blog/classify/${BlogTopic.blogClassify.cid}/article">${BlogTopic.blogClassify.name}</a>]</span>
			       <span class="right">评论[${BlogTopic.replycount}]</span>
			       <span class="right">浏览[${BlogTopic.viewcount}]</span>
			   </p>
		  </div>
	  </c:forEach>
 </div>
  <aside class="right">
    <div class="weather"><iframe width="250" scrolling="no" height="60" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=1"></iframe></div>
    <div class="news">
    <h3>
      <p>最新<span>文章</span></p>
    </h3>
    <ul class="rank">
    	<c:forEach items="${topiclastest5}" var="BlogTopic">
    		<li><a href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a></li>
    	</c:forEach>
    </ul>
    <h3 class="view">
      <p>浏览<span>排行</span></p>
    </h3>
    <ul class="paih">
    	<c:forEach items="${topicViewCountTop8}" var="BlogTopic">
    		<li><a href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a></li>
    	</c:forEach>
    </ul>
    <h3 class="ph">
      <p>评论<span>排行</span></p>
    </h3>
    <ul class="paih">
        <c:forEach items="${topicReplyCountTop8}" var="BlogTopic">
    		<li><a href="${base}/blog/article/${BlogTopic.bid}" title="${BlogTopic.title}" target="_blank">${BlogTopic.title}</a></li>
    	</c:forEach>
    </ul>
    <h3 class="links">
      <p>友情<span>链接</span></p>
    </h3>
    <ul class="website">
    	<c:forEach items="${friendlinks}" var="Friendlink">
    		<li><a href="${Friendlink.url}" target="_blank">${Friendlink.name}</a></li>
    	</c:forEach>
    </ul> 
    </div>  
    <!-- Baidu Button BEGIN -->
    <div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"><a class="bds_tsina"></a><a class="bds_qzone"></a><a class="bds_tqq"></a><a class="bds_renren"></a><span class="bds_more"></span><a class="shareCount"></a></div>
    <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=6574585" ></script> 
    <script type="text/javascript" id="bdshell_js"></script> 
    <script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000)
</script> 
    <!-- Baidu Button END -->   
    <a title="扫描二维码加好友"  class="weixin"> </a></aside>
    </aside>
</article>
<jsp:include page="/WEB-INF/pages/public/common/footer.jsp"></jsp:include>
<script src="${base}/static/js/silder.js"></script>
</body>
</html>