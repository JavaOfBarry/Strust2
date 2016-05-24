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
    
    <title>Insert page</title>
    
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
		<h3>新增</h3>
	</div>

	<font color="red"><s:fielderror/></font>
	<s:form action="insertUser" theme="simple" enctype="multipart/form-data">
		<table align="center" border="1">
			<tr>
				<td>name:</td>
				<td><s:textfield name="name"></s:textfield>
				 <td><s:fielderror fieldName="name"/></td>
				</td>
			</tr>
			<tr>
			<tr>
				<td>password:</td>
				<td><s:textfield name="password"></s:textfield>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><s:submit value=" submit "></s:submit> <s:reset
						value=" reset "></s:reset></td>

			</tr>
		</table>
	</s:form>

</body>
</html>
