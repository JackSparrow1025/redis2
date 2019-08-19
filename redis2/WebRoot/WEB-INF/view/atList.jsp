<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'atList.jsp' starting page</title>
    
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


<style type="text/css">
#loginModal{
  position: absolute;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -moz-transform:  translateY(-50%);
  -ms-transform:  translateY(-50%);
  -o-transform:  translateY(-50%);
  transform:  translateY(-50%);
  background-color: #eee;
}
</style>





  </head>
  
  <body>
  
  
  
   <div class="modal show" id="loginModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close">&times;</button>
                    <h1 class="text-center text-primary">登录</h1>
                </div>
                <div class="modal-body">
                    <form class="form col-md-12 center-block" id="loginForm" action="main/successLogin.do" method="post">
                        <div class="form-group-lg"  id="accountDiv">
                            <label class="sr-only" for="inputAccount">账号</label>
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
                                <input class="form-control" id="inputAccount" name="accountNo" type="text" placeholder="账号" required autofocus>
                            </div>
                            <div class="hidden text-center" id="accountMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名不存在</div>
                        </div>
                        <br>
                        <div class="form-group-lg" id="pwdDiv">
                            <label class="sr-only" for="inputPassword">密码</label>
                            <div class="input-group">
                                <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                                <input class="form-control" id="inputPassword" name="pwd" type="password" placeholder="密码" required>
                                <div class="input-group-addon"><span class="glyphicon glyphicon-eye-open"></span></div>
                            </div>
                            <div class="hidden text-center" id="pwdMsg"><span class="glyphicon glyphicon-exclamation-sign"></span>用户名密码错误</div>
                        </div>
                        <div class="checkbox">
                            <label> <input type="checkbox" value="remember-me">记住我</label>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-default btn-lg col-md-6" id="btn_register" type="submit">注册</button>
                            <button class="btn btn-primary btn-lg col-md-6" id="btn_login" type="button" >登录</button>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">

                </div>
            </div>
        </div>
    </div>
  
  
  
  
  
  
  
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
 <h1>Redis查询加bootstrap</h1>
  
  
    
    <table border="1px;">
     <tr>
      <th>id</th>
      <th>时间</th>
      <th>姓名</th>
      <th>内容</th>
      <th>操作</th>
     </tr>
     
     <c:forEach items="${articleList }" var="article">
      <tr>
       <td>${article.id }</td>
       <td><fmt:formatDate value="${article.time }"/></td>
       <td>${article.name }</td>
       <td>${article.contentValue }</td>   
       <td><a href="<c:url value='/article/deleteArticle.action?aid=${article.id }'/>">删除</a></td>  
      </tr>
     </c:forEach>
     
    
    </table>
    
    
    
    
  </body>
</html>
