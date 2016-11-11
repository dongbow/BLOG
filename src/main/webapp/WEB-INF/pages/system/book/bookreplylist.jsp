<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript">

    var dataGrid;

    $(function() {

        dataGrid = $('#dataGrid').datagrid({
            url : '${path}/system/admin/book/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'createtime',
            sortOrder : 'asc',
            pageSize : 10,
            pageList : [ 10, 20, 30, 40, 50, 100 ],
            columns : [ [ 
			{
			    width : '50',
			    title : 'ID',
			    field : 'id'
			},{
                width : '200',
                title : '内容',
                field : 'content'
            }, {
                width : '50',
                title : '回复数',
                field : 'replycount'
            },{
                width : '50',
                title : '状态',
                field : 'isdelete',
                formatter : function(value, row, index) {
                	if(value == 0)
                        return '正常';
                    if(value == 1)
                        return '已删除';
                }
            },{
                width : '60',
                title : '用户ID',
                field : 'uid',
                formatter : function(value,row){
                	return row.user.uid;
                }
            },{
                width : '100',
                title : '用户昵称',
                field : 'nickname',
                formatter : function(value,row){
                	return row.user.nickname;
                }
            },{
                width : '100',
                title : '创建时间',
                field : 'createtime',
                sortable : true
            },{
                width : '60',
                title : '父ID',
                field : 'id',
                formatter : function(value,row){
                	return row.parentReply.id;
                }
            }, {
            	width : '130',
            	title : '操作',
                field : 'createtime',
                formatter : function(row, index) {
                    var str = '';
                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="editFun(\'{0}\');" >编辑</a>', row.id);
                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-del" data-options="plain:true,iconCls:\'icon-del\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
                    return str;
                }
            }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
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
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function deleteFun(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
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
                            dataGrid.datagrid('reload');
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
            var rows = dataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/user/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }
    function cleanFun() {
        $('#searchForm input').val('');
        dataGrid.datagrid('load', {});
    }
    </script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchForm">
            <table>
                <tr>
                    <th>ID:</th>
                    <td><input name="id" placeholder="请输入ID"/></td>
                    <th>内容:</th>
                    <td><input name="content" placeholder="请输入关键字"/></td>
                    <th>状态:</th>
                    <td>
                    	<input class="easyui-combobox" 
							name="isdelete"
							data-options="
									url:'${base}/json/isdelete.json',
									method:'get',
									valueField:'id',
									textField:'value',
									multiple:true,
									panelHeight:'auto',
									width:70
						">
                    </td>
                    <th>用户ID:</th>
                    <td><input name="user_id" placeholder="请输入用户ID"/></td>
                    <th>昵称:</th>
                    <td><input name="nickname" placeholder="请输入用户昵称"/></td>
                    <th>创建时间:</th>
                    <td>
                    <input name="starttime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至<input  name="endtime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'回复列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display:block;">
        <a onclick="deletelistFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">批量删除</a>
    </div>
</body>
</html>