<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(function () {
    
    $('#addClassify').form({
        url : '<%=basePath%>/system/admin/classify/addClassify',
        onSubmit : function() {
            progressLoad();
            var isValid = $(this).form('validate');
            if (!isValid) {
                progressClose();
            }
            return isValid;
        },
        success : function(result) {
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
                parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                parent.$.modalDialog.handler.dialog('close');
            } else {
                parent.$.messager.alert('提示', result.msg, 'warning');
            }
        }
    });
});
</script>
<form id="addClassify" action="" method="post">
	<div class="easyui-panel" style="width:340px;padding:20px">
		<div style="margin-bottom:8px;width: 100%;">
			<div>分类:</div>
			<input name="name" class="easyui-textbox" style="height:32px;width: 100%;">
		</div>
		<div style="margin-bottom:8px;width: 100%;">
			<div>状态:</div>
			<input class="easyui-combobox" 
				name="isdelete"
				data-options="
						url:'<%=basePath%>/json/status.json',
						method:'get',
						valueField:'id',
						textField:'value',
						multiple:true,
						panelHeight:'auto',
						width:240,
						height:32
			">
		</div>
	</div>
</form>
</body>
</html>