<%@tag pageEncoding="UTF-8"%>
<%@attribute name="page" type="cn.ifxcode.bean.Page" required="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	int current =  page.getPageNo();
	int begin = 1;
	int end = page.getTotalPage();
	
	request.setAttribute("current", current);
	request.setAttribute("begin", begin);
	request.setAttribute("end", end);
	request.setAttribute("pList", page.getPageNoDisp());
	System.out.println(page.getPageNoDisp());
%>
<script type="text/javascript">
  var paras = '<%=page.getParaJson()%>';
  var paraJson = eval('(' + paras + ')');

  //将提交参数转换为JSON
  var paraLists = '<%=page.getParaListJson()%>';
  var paraListJson = eval('(' + paraLists + ')');
  function pageClick( pNo ){
    paraJson["pageNo"] = pNo;
    paraJson["pageSize"] = "<%=page.getPageSize()%>";
    
    var jsGet = function(action, values, valueLists) {
      var id = Math.random();
      document.write('<form id="post' + id + '" name="post'+ id +'" action="' + action + '" method="get">');
      for (var key in values) {
        document.write('<input type="hidden" name="' + key + '" value="' + values[key] + '" />');
      	break;
      }
      for (var key2 in valueLists) {
        for (var index in valueLists[key2]) {
          document.write('<input type="hidden" name="' + key2 + '" value="' + valueLists[key2][index] + '" />');
        }
      }
      document.write('</form>');    
      document.getElementById('post' + id).submit();
    }
    
    //发送POST
    jsGet("<%=page.getSearchUrl()%>", paraJson, paraListJson);
  }
</script>
  <% if (current!=1 && end!=0){%>
    <a title="首页" href="javascript:;" onclick="pageClick(1)">&lt;&lt;</a>
    <a title="上一页" href="javascript:;" onclick="pageClick(${current-1})">&lt;</a>
  <%}%>
  <%
  	String plist =page.getPageNoDisp();
  	for(int i = 0 ; i<plist.split(",").length; i++){ 
  		String pNo =plist.split(",")[i]; 
  		if(pNo.equals("0")){%>
  			 <label style="font-size: 10px; width: 20px; text-align: center;">•••</label>
  		<%}else if(!pNo.equals(Integer.toString(current))) {
  			%>
  			<a title="第<%=pNo %>页" href="javascript:;" onclick="pageClick(<%=pNo %>)"><%=pNo %></a>
  		<%}else{
  			%>
  			<b><%=pNo %></b>
  		<%}
  	}
  %>

  <% if (current<end && end!=0){%>
    <a title="下一页" href="javascript:;" onclick="pageClick(${current+1})">&gt;</a>
    <a title="尾页" href="javascript:;" onclick="pageClick(${end})">&gt;&gt;</a>
  <%}%>