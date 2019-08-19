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
    
    <title>My JSP 'pageList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>


<div>

<table>

<c:forEach items="${articleListPage.list }" var="article" varStatus="status">
<tr>
 <td>${status.count }</td>
 <td>${article.name }</td>
 <td>${article.time }</td>
</tr>
</c:forEach>



</table>

</div>

			<div class='pages_bar'>
		 <a href="<c:url value='/article/listPage.action?pageNum=${articleListPage.firstPage}'/>" id="first">首页</a>
			<c:choose>
			  <c:when test="${articleListPage.pageNum==1 }">
			      <span></span>
			  </c:when>
			  <c:otherwise>
			      <a href="<c:url value='/article/listPage.action?pageNum=${articleListPage.prePage}'/>" id="first">上一页</a>
			  </c:otherwise>
			</c:choose>
				
			<c:forEach begin="1" end="${articleListPage.pages}" var="p" varStatus="i">
			    <a href="<c:url value='/article/listPage.action?pageNum=${p }'/>">${p }</a>
			</c:forEach>			
			<c:choose>
			   <c:when test="${articleListPage.pageNum==articleListPage.pages }">
			      	  <span></span>
			   </c:when>
			   <c:otherwise>
			     <a href="<c:url value='/article/listPage.action?pageNum=${articleListPage.nextPage}'/>" id="first">下一页 </a>
			   </c:otherwise>
			</c:choose>
				
		 <a href="<c:url value='/article/listPage.action?pageNum=${articleListPage.lastPage}'/>" id="last" >尾页</a> 
			<span>当前第${articleListPage.pageNum}页/共${articleListPage.pages}页</span>
			
			</div>
		

















  </body>
</html>
