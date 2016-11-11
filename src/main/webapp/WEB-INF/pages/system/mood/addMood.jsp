<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
$(function () {
    
    $('#addMoodAction').form({
        url : '<%=basePath%>/system/admin/mood/addMood',
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
    
    CKEDITOR.replace('moodeditor',{ 
    	toolbar: 'mood', 
    	height: '390px', 
    	width: '792px', 
    	removePlugins: 'elementspath',
   		resize_enabled: false 
   	});
});
</script>
<form id="addMoodAction" method="post">
	<textarea id="moodeditor" name="content" rows="12" cols="93"></textarea>
</form>
</body>
</html>