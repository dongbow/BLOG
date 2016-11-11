<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<base href="${base}/">
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理-爱分享</title>
<script type="text/javascript" src="${path}/plugins/ckeditor4/ckeditor.js"></script>
<link href="${base}/static/css/about.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${path}/static/js/Common.js"></script>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var layout_west_tree;

    $(function() {
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    index_tabs.tabs('getTab', index).panel('open').panel('refresh');
                }
            }, {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });
    });



    function addTab(title, href, icon) {
        var tt = $('#index_tabs');
        icon = icon || 'menu_icon_service';
        if (tt.tabs('exists', title)) {
            tt.tabs('select', title);
            var currTab = tt.tabs('getTab', title);
            tt.tabs('update', {tab: currTab, options: {content: content, closable: true}});
        } else {
            if (href) {
                var content = '<iframe frameborder="0" src="' + href + '" style="border:0;width:100%;height:99.5%;"></iframe>';
            } else {
                var content = '未实现';
            }
            tt.tabs('add', {
                title : title,
                content : content,
                closable : true,
                iconCls: icon
            });
        }
    }

    function logout(e){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                window.location = '${path}/account/logout?backurl=${base}/index';
                progressLoad();
            }
        });
    }

    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 300,
            height : 230,
            href : '${path }/system/admin/user/editPwdPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }

</script>
</head>
<body>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div data-options="region:'north',border:false" style="hight:65px; overflow: hidden; ">
            <div>
                <span style="float: right; padding-right: 20px; margin-top: 15px; color: #333">欢迎 <b>${user.nickname}</b>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="javascript:addTab('写博客','${base}/system/admin/blog/addBlogPage','icon-edit')" 
                	class="easyui-linkbutton" plain="true" icon="icon-add" >写博客</a>&nbsp;&nbsp;
                <a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton" plain="true" icon="icon-edit" >修改密码</a>&nbsp;&nbsp;
                <a href="${path}/index" class="easyui-linkbutton" plain="true" icon="icon-remove">回到前台</a>
                <a href="javascript:void(0)" onclick="logout(this)" class="easyui-linkbutton" plain="true" icon="icon-clear">安全退出</a></span>
                <span class="header"></span>
            </div>
        </div>
        <div data-options="region:'west',split:true" title="菜单" style="width: 160px; overflow: hidden;overflow-y:auto; padding:0px">
            <div class="easyui-accordion  i_accordion_menu" fit="true" border="false">

                <div title="系统管理" selected="true" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('用户管理','${path}/system/admin/userlist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>用户管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('角色管理','${path}/system/admin/rolelist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>角色管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('资源管理','${path}/system/admin/resourcelist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>资源管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('数据字典','${path}/system/admin/ddl','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>数据字典</span>
                        </a>
                    </div>
                </div>

                <div title="博客管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('博文管理','${path}/system/admin/bloglist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>博文管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('首页博文','${path}/system/admin/homeBloglist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>首页博文</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('分类管理','${path}/system/admin/blogclassifylist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>分类管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('标签管理','${path}/system/admin/blogsignlist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>标签管理</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('评论管理','${path}/system/admin/blogreplylist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>评论管理</span>
                        </a>
                    </div>
                </div>

                <div title="心情管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('心情管理','${path}/system/admin/moodlist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>心情管理</span>
                        </a>
                    </div>
                </div>
                
                <div title="留言管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('留言管理','${path}/system/admin/bookreplylist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>留言管理</span>
                        </a>
                    </div>
                </div>
                                
                 <div title="消息管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('我的消息','${path}/system/admin/mymessage','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>我的消息</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('消息列表','${path}/system/admin/messagelist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>消息列表</span>
                        </a>
                    </div>
                </div>
                
                <div title="日志管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('登录日志','${path}/system/admin/loginloglist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>登录日志</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('操作日志','${path}/system/admin/dongloglist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>操作日志</span>
                        </a>
                    </div>
                </div>
                
                <div title="备份管理" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('备份管理','${path}/system/admin/backuplist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>备份管理</span>
                        </a>
                    </div>
                </div>
                
                <div title="数据分析" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('页面分享','http://share.baidu.com/analysis','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>页面分享</span>
                        </a>
                    </div>
                </div>
                
                 <div title="系统监控" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('告警列表','${path}/system/admin/report','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>告警列表</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('实时监控','${path}/system/admin/runtime','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>实时监控</span>
                        </a>
                    </div>
                </div>
                
                <div title="系统设置" style="overflow: auto;">
                    <div class="nav-item">
                        <a href="javascript:addTab('系统信息','${path}/system/admin/systeminfo','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>系统信息</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('系统导航','${path}/system/admin/navigationlist','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>系统导航</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('友情链接','${path}/system/admin/friendlink','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>友情链接</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('个人信息','${path}/system/admin/userinfo','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>个人信息</span>
                        </a>
                    </div>
                    <div class="nav-item">
                        <a href="javascript:addTab('关于我','${path}/system/admin/about','menu_icon_datadeal')">
                            <span class="menu_icon_datadeal"></span>
                            <span>关于我</span>
                        </a>
                    </div>
                </div>

            </div>

        </div>
        <div data-options="region:'center'" style="overflow: hidden;">
            <div id="index_tabs" style="overflow: hidden;">
                <div title="首页" data-options="border:false" style="overflow: hidden;">
                    <script src=''></script>
                    <style>
                        .pro_name a{color: #4183c4;}
                        .osc_git_title{background-color: #d8e5f1;}
                        .osc_git_box{background-color: #fafafa;}
                        .osc_git_box{border-color: #ddd;}
                        .osc_git_info{color: #666;}
                        .osc_git_main a{color: #4183c4;}
                    </style>
                </div>
            </div>
        </div>
    </div>

    <!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->

    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>