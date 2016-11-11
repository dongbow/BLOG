<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${base}/">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript" src="${base}/plugins/ckeditor4/ckeditor.js"></script>
<script type="text/javascript">
$(function () {
	$(document).ready(function(){
		CKEDITOR.replace('blogeditor',{ 
	    	toolbar: 'post', 
	    	height: '345px', 
	    	width: '920px', 
	    	removePlugins: 'elementspath',
	   		resize_enabled: false 
	   	});
	});
	
	$('#post').click(function(){
		var $btn= $('#addBlogAction');
		$btn.attr('action','${base}/system/admin/blog/addBlogTopic');
		$btn.submit();
	});
});
</script>
<style>
#cke_blogeditor.cke_chrome {
  box-shadow: 0 0 3px rgba(0, 0, 0, 0);
}
</style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" style="margin:0 100px;">
		<form id="addBlogAction" method="post">
			<div style="margin-top:10px">
				<input class="easyui-combobox" 
					name="createOrRepost"
					data-options="
							url:'${base}/json/createOrRepost.json',
							method:'get',
							valueField:'createOrRepost',
							textField:'value',
							multiple:true,
							panelHeight:'auto',
							width:100,
							height:25
				">
				<input class="easyui-combobox" 
					name="cid"
					data-options="
							url:'${base}/system/admin/classify/json',
							method:'post',
							valueField:'cid',
							textField:'name',
							multiple:true,
							panelHeight:'auto',
							width:240,
							height:25
				">
			</div>
			<div style="margin:10px 0;">
				<input name="title" class="easyui-textbox" style="width:77%;height:25px">
			</div>
			<textarea id="blogeditor" name="content"></textarea>
			<div style="margin:10px 0">
				<div>标签 (多个标签以“,”号隔开)</div>
				<input name="signs" class="easyui-textbox" style="width:77%;height:25px">
			</div>
		</form>
		<a id="post" class="easyui-linkbutton" data-options="iconCls:'icon-edit'" href="javascript:void(0)" style="width:80px">发表</a>
	</div>
</div>
</body>
</html>