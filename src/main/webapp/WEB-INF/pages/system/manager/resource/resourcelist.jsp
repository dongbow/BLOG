<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript">

    var treeGrid;

    $(function() {

    	treeGrid = $('#treeGrid').treegrid({
            url : '${path}/system/admin/resource/treeGrid',
            idField : 'resid',
            treeField : 'resname',
            parentField : 'pid',
            fit : true,
            fitColumns : false,
            border : false,
            frozenColumns : [ [ {
                title : 'ID',
                field : 'resid',
                width : 50
            } ] ],
            columns : [ [ {
            	field : 'resname',
            	title : '资源名',
                width : 100
            }, {
            	field : 'resurl',
            	title : '资源url',
                width : 160
            }, {
            	field : 'ressign',
            	title : '标识',
                width : 150
            }, {
            	field : 'pid',
            	title : '上级ID',
                width : 60
            }, {
            	field : 'resattr',
            	title : '类型',
                width : 80,
                formatter : function(value, row, index) {
                	if(value == 0)
                        return '顶级目录';
                    if(value == 1)
                        return '菜单';
                    if(value == 2)
                    	return '按钮';
                }
            }, {
            	field : 'resico',
            	title : '图标',
                width : '160'        
            },{
            	field : 'resstatus',
            	title : '状态',
                width : 50,
                formatter : function(value, row, index) {
                	if(value == 0)
                        return '正常';
                    if(value == 1)
                        return '停用';
                }
            },{
            	field : 'rescreatetime',
            	title : '创建时间',
                width : 100
            }, {
                field : 'resdesc',
                title : '操作',
            	width : 80,
                formatter : function(value, row, index) {
                    var str = '';
                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
            },
            toolbar : '#toolbar'
        });
    });
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/user/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = treeGrid.treegrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
        	treeGrid.treegrid('unselectAll').treegrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
            if (b) {
                var currentUserId = '${sessionInfo.id}';/*当前登录用户的ID*/
                if (currentUserId != id) {
                    progressLoad();
                    $.post('${path }/user/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            treeGrid.treegrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                } else {
                    parent.$.messager.show({
                        title : '提示',
                        msg : '不可以删除自己！'
                    });
                }
            }
        });
    }
    
    function editFun(id) {
        if (id == undefined) {
            var rows = treeGrid.treegrid('getSelections');
            id = rows[0].id;
        } else {
        	treeGrid.treegrid('unselectAll').treegrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/user/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
    }
    </script>
</head>
<body>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'center',border:false"  style="overflow: hidden;">
            <table id="treeGrid"></table>
        </div>
    </div>
    <div id="toolbar" style="display:block;">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
        <a onclick="stopFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-lock'">停用</a>
        <a onclick="startFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-man'">启用</a>
    </div>
</body>
</html>