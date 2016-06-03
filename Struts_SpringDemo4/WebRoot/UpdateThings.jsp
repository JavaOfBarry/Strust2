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
<div style="margin-left:100px;">
<h3>修改</h3>
</div>
  

<body>
	<s:form action="updateThings" method="post">
	<s:hidden name = "#request.thing.id"></s:hidden>
		<s:textfield name="#request.thing.name" label="商品名称"  />
		<s:textfield name="#request.thing.price" label="商品价格" />
		<s:textarea name="#request.thing.description"
			cssStyle="size:10px; width:160px;height:60px" label="商品描述" />
		<s:iterator id="img" value="#request.thing.picture">
			<div style="text-align:center; width:100%; height:100%;" >
				<img src="${img}" style="vertical-align:middle;" width="100"
					height="100" />
			</div>
		</s:iterator>
		<s:submit></s:submit>
	</s:form>
</body>
</html>
