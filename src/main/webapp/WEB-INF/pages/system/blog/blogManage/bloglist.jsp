<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/commons/basejs.jsp" %>
<script type="text/javascript" src="${base}/static/js/Commmon.js"></script>
<script type="text/javascript">

var dataGrid;

$(function() {

    dataGrid = $('#dataGrid').datagrid({
        url : '${path}/system/admin/blog/dataGrid',
        fit : true,
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'bid',
        sortName : 'createtime',
        sortOrder : 'asc',
        pageSize : 10,
        pageList : [ 10, 20, 30, 40, 50, 100 ],
        columns : [ [ 
		{
		    width : '50',
		    title : 'ID',
		    field : 'bid'
		},{
		    width : '50',
		    title : '性质',
		    field : 'createOrRepost',
		    formatter : function(value,row,index){
		    	if(value == 0){
		    		return "原创";
		    	}
		    	if(value == 1){
		    		return "转载";
		    	}
		    }
		},{
            width : '150',
            title : '标题',
            field : 'title'
        },{
            width : '200',
            title : '描述',
            field : 'description'
        },{
            width : '60',
            title : '阅读数',
            field : 'viewcount'
        },{
            width : '60',
            title : '评论数',
            field : 'replycount'
        },{
            width : '60',
            title : '赞',
            field : 'praisecount'
        },{
            width : '60',
            title : '踩',
            field : 'notpraisecount'
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
            width : '50',
            title : '分类ID',
            field : 'cid',
            formatter : function(value,row){
            	return row.blogClassify.cid;
            }
        },{
            width : '150',
            title : '分类名字',
            field : 'name',
            formatter : function(value,row){
            	return row.blogClassify.name;
            }
        },{
            width : '100',
            title : '创建时间',
            field : 'createtime'
        },{
        	width : '130',
        	title : '操作',
            field : 'content',
            formatter : function(row, index) {
                var str = '';
                	str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'icon-edit\'" onclick="cancelFun(\'{0}\');" >编辑</a>', row.id);
                	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                	str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-ok" data-options="plain:true,iconCls:\'icon-ok\'" onclick="cancelFun(\'{0}\');" >推送</a>', row.id);
                return str;
            }
        }] ],
        onLoadSuccess:function(data){
        	$('.user-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
            $('.user-easyui-linkbutton-ok').linkbutton({text:'推送',plain:true,iconCls:'icon-ok'});
        },
    	toolbar : '#toolbar'
    });
});
    
    function addFun() {
    	var title = '写博客';
    	var url = '${base}/system/admin/blog/addBlogPage';
    	var icon = 'icon-edit';
    	var jq = top.jQuery;    
        
        if(jq("#index_tabs").tabs('exists', title)){    
            jq("#index_tabs").tabs('select', title);    
        }else{  
            var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';     
             jq("#index_tabs").tabs('add',{    
                 title:title,    
                 content:content,    
                 closable:true,
                 icon: icon
             });    
         } 
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
                	<th>博客ID</th>
                	<td><input name="bid" placeholder="请输入博客ID"/></td>
                	<th>分类ID</th>
                	<td><input name="cid" placeholder="请输入所属分类ID"/></td>
                	<th>分类名</th>
                	<td><input name="name" placeholder="请输入所属分类名"/></td>
                	<th>性质:</th>
                    <td>
                    	<input class="easyui-combobox" 
							name="createOrRepost"
							data-options="
									url:'${base}/json/createOrRepost.json',
									method:'get',
									valueField:'createOrRepost',
									textField:'value',
									multiple:true,
									panelHeight:'auto',
									width:70
						">
                    </td>
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
                    <th>创建时间:</th>
                    <td>
                    <input name="starttime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                    	至
                    <input  name="endtime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                    </td>
                    <td>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'标签列表'" >
        <table id="dataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="toolbar" style="display:block;">
        <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">写博客</a>
        <a onclick="deletelistFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">批量删除</a>
    </div>
</body>
</html>