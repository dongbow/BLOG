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
<style type="text/css">
	.exportpanel{width:520px;height:auto;overflow:hidden;padding:10px;margin:0 auto;background:#fff;}
	.leftchoose,.rightresult{width:210px;height:auto;overflow:hidden;border:1px solid #ddd;min-height:350px;}
	.leftchoose{float:left;}
	.rightresult{float:right;}
	.centertool{width:76px;float:left;overflow:hidden;height:auto;min-height:260px;margin:0 10px;text-align:center;padding-top:90px;}
	.centertool a{display:block;text-decoration:none;color:#333;margin-bottom:15px;width:64px;padding:5px;height:20px;border:1px solid #ddd;
				  border-radius:5px;}
	.centertool a:hover{color:#19b4ea;border:1px solid #19b4ea;}
	option{color:#444;font-size:16px;width: 182px;margin-bottom:2px;}
</style>
<script type="text/javascript">
	$(function(){
		$('.all').click(function(){
			if($('.leftchoose').find('option').length != 0){
				$('.leftchoose').find('option').prop('selected','selected');
			}else{
				$('.rightresult').find('option').prop('selected','selected');
			}
		});
		$('.zero').click(function(){
			if($('.leftchoose').find('option').length != 0){
				$('.leftchoose').find('option').removeAttr('selected','selected');
			}else{
				$('.rightresult').find('option').removeAttr('selected','selected');
			}
		});
	});
</script>
<script language="JavaScript">
function copyToList(from,to) //from表示:包含可选择项目的select对象名字 to表示:列出可选择项目的select对象名字 //你可以根据你的具体情况修改
{
  fromList = eval('document.forms[0].' + from);
  toList = eval('document.forms[0].' + to);
  if (toList.options.length > 0 && toList.options[0].value == 'temp')
  {
    toList.options.length = 0;
  }
  var sel = false;
  for (i=0;i<fromList.options.length;i++)
  {
    var current = fromList.options[i];
    if (current.selected)
    {
      sel = true;
      if (current.value == 'temp')
      {
    	  $.messager.alert ('错误提示','你不能选择这个项目!',"warning");
        return;
      }
      txt = current.text;
      val = current.value;
      toList.options[toList.length] = new Option(txt,val);
      fromList.options[i] = null;
      i--;
    }
  }
  if (!sel) $.messager.alert ('错误提示','你还没有选择任何项目',"warning");
}
function allSelect() //这是当用户按下提交按钮时，对列出选择的select对象执行全选工作，让递交至的后台程序能取得相关数据
{
  List = document.forms[0].chosen;
  if (List.length && List.options[0].value == 'temp') return;
  for (i=0;i<List.length;i++){
     List.options[i].selected = true;
  }
}

</script>
<div class="exportpanel">
	<form onSubmit="allSelect()">
		<div class="leftchoose">
			<select name="possible" MULTIPLE style="width: 218px;margin:10px;border:none;min-height:330px">
				<option value="1">用户ID</option>
				<option value="2">用户登录名</option>
				<option value="3">用户昵称</option>
				<option value="4">用户角色</option>
				<option value="5">用户邮箱</option>
				<option value="6">用户性别</option>
				<option value="7">用户生日</option>
				<option value="8">省份</option>
				<option value="9">城市</option>
				<option value="10">用户QQ</option>
				<option value="11">上次登录时间</option>
				<option value="12">上次登录IP</option>
				<option value="13">注册时间</option>
				<option value="14">注册IP</option>
			</select>
		</div>
		<div class="centertool">
			<a class="all" href="javascript:;">全选</a>
			<a class="zero" href="javascript:;">全不选</a>
			<a class="mright" href="javascript:copyToList('possible','chosen')">&gt;&gt;</a>
			<a class="mleft" href="javascript:copyToList('chosen','possible')">&lt;&lt;</a>
		</div>
		<div class="rightresult">
			<select name="chosen" MULTIPLE style="width: 218px;margin:10px;border:none;min-height:330px">
			</select>
		</div>
	</form>
</div>
</body>
</html>