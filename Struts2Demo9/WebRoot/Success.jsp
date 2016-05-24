<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Success page</title>

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
		<h3>登录成功</h3>
		</br>
		<h4>欢迎</h4>
		姓名:${sessionScope.user.name} 密码:
		<s:property value="#session.user.password" />

		<s:form action="selectUser" method="post">
			<s:submit value="用户管理" />
		</s:form>
		<s:form action="selectThings" method="post">
			<s:submit value="商品管理" />
		</s:form>
		<s:form action="selectUserJd" method="post">
			<s:submit value="京东前世" />
		</s:form>
		<s:form action="quit" method="post">
			<s:submit value="退出系统" />
		</s:form>
	</div>
</body>
</html>
