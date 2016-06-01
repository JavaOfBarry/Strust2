<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My page</title>
    
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
	<div style="text-align:center;">
		<h4>欢迎您</h4>
		姓名:${sessionScope.user.username} 密码:
		<s:property value="#session.user.password" />
	</div>
	<table align="center" width="70%" cellspacing="0" cellpadding="0" border=1>
      <tr align="center">
        <td>name</td><td>password</td><td>操作</td>
      </tr>
     <s:iterator value="#request.user" >
			<tr>
				<td><s:property value="username" /> </td>
				<td><s:property value="password" /></td>
				
				<td align="center"><a
					href="selectUpdateUser.action?id=<s:property value='id'/>">修改</a> <a
					href="deleteUser.action?id=<s:property value='id'/>">删除</a></td>
			</tr>
		</s:iterator>
    <tr>
    <td ><a href="<s:url value="/InsertUser.jsp"/>">新增</a></td>
     <td><a href="<s:url value="/Success.jsp"/>">返回</a></td>
    </tr>
    </table>
    
  </body>
</html>
