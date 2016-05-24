<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.wwb.bean.*" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<User> car = new ArrayList<User>();	
float count = 0;	
car = (List<User>) session.getAttribute("car");
if(car!=null){
for(User cc :car){
count = count + Float.parseFloat(cc.getName())*cc.getSum();
}
}
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
		<h4>欢迎</h4>
		姓名:${sessionScope.user.name} 密码:
		<s:property value="#session.user.password" />
	</div>
	<table align="center" width="70%" cellspacing="0" cellpadding="0" border=1>
      <tr align="center">
        <td>商品名称</td><td>商品价格</td><td>商品图片</td><td>商品数量</td><td>操作</td>
      </tr>
     <s:iterator value="#session.car" >
			<tr>
				<td><s:property value="id" /> </td>
				<td><s:property value="name" /></td>
				<td>
				<s:iterator id="img1" value="file">
				<div style="text-align:center; width:100%; height:100%;">
				<img src="${img1}" style="vertical-align:middle;" width="100" height="100" />
				</div>
    			</s:iterator>
    			<td><s:property value="sum" /></td>
				</td>
				<td align="center"> <a
					href="deleteCar.action?zz=<s:property value='zz'/>&id=<s:property value='id'/>">删除商品</a></td>
			</tr>
		</s:iterator>
		 <tr>
    <td >总额：</td>
    <td ><%=count %></td>
    </tr>
    <tr>
    <td ><a href="selectUserJd.action"/>返回</a></td>
    <td ><a href="quit.action"/>退出系统</a></td>
    </tr>
    </table>
    如需购买商品请直接转账本人支付宝账号即可，简洁方便，轻松大方。</br>
    账号：HahaJiang@qq.com
  </body>
</html>
