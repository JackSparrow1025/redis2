<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	
	
	
	
	
	
  </head>
<script type="text/javascript">
function search(){
//location.href="<c:url value='/article/search.action'/>";
window.location.href="<c:url value='/article/search.action'/>";
}


/* function showWord(){        //这个是POI转Html的方法
 $.ajax({
   type:"POST",
   url:"<c:url value='/article/showWordToHtml.action'/>",
   //async:false,
   success:function(){
   window.open("MyHtml.html",null,"width=1100,height=700,Left=200px,Top=100px,menubar=no,titlebar=no,scrollbar=no,toolbar=no,status=no,location=no");
   
   }
 });
} */

function showHtmlPage(){   
    $.ajax({
   type:"POST",
   url:"<c:url value='/article/openOfficeShowWordToHtml.action'/>",
   //async:false,
   success:function(htmlString){
   
   $("#ttt").val(htmlString);
   
   $("#showOpenOfficeDiv").html(htmlString);

   
   window.open("show.jsp",null,"width=1100,height=700,Left=200px,Top=100px,menubar=no,titlebar=no,scrollbar=no,toolbar=no,status=no,location=no");
   
   }
 });
}



</script>  
  
  
  <body>
   
  
   
   <input type="button"  value="查询" onclick="search()">
   
   
   
   
     <div class="btn-group" style="position:absolute; top:160px">
    <button class="btn btn-primary" id="selectAllBtn">全  选</button>
    <div class="btn-group">
        <button data-toggle="dropdown" class="btn btn-success dropdown-toggle" aria-expanded="false">
            操  作
            <span class="caret"></span>
        </button>           
        <ul class="dropdown-menu">
            <li><a href="javascript:void(0)" class="operationLi" id="approval">审批</a></li>
            <li><a href="javascript:void(0)" class="operationLi" id="add">增加</a></li>
            <li><a href="javascript:void(0)" class="operationLi" id="edit">编辑</a></li>
            <li><a href="javascript:void(0)" class="operationLi" id="delete">删除</a></li>
        </ul>
    </div>
   </div>
   
   
   
   <p class="navbar-text navbar-center">Signed in as <a href="#" class="navbar-link">Mark Otto</a></p>
   
   
   
   <a href="#" class="navbar-link">a标签链接</a>
   
   
   
   
   
   
   
   
   
<div>
 <a href="<c:url value='/article/listPage.action'/>">查询并分页</a>

</div>   










<input type="button" value="预览" onclick="showWord()">


<br>


<input type="button" value="openOffice打开html页面" onclick="showHtmlPage()">
   
   
<div id="showOpenOfficeDiv">


</div>   
   
   
<%-- <img alt="" src="<c:url value='uploadFile/1540963782181_html_m1af43992.jpg'/>">   
  --%>  
   
<textarea rows="2" cols="50" id="ttt"></textarea>   
   
   
  </body>
</html>
