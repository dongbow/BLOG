<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/basejs.jsp" %>
<link href="${base}/static/css/about.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
    
    function addFun() {
        parent.$.modalDialog({
            title : '修改',
            width : 800,
            height : 500,
            href : '${path }/system/admin/updateAbout',
            buttons : [ {
                text : '修改',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#updateAboutAction');
                    f.submit();
                }
            } ]
        });
    }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
		<div id="toolbar" style="display:block;">
	        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">修改</a>
	    </div>
    </div>
	<div data-options="region:'center',border:true,title:'Now'" style="overflow: auto;">
        <div style="width:1000px;margin:0 auto">
         	<c:import url="${base}/about/left"></c:import>
        </div>
    </div>
</body>
</html>