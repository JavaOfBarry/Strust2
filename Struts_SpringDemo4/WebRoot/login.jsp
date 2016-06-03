<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录页面</title>
</head>
<body>
<h3>用户登录</h3>
<s:form action="loginPro">
    <s:textfield name="username" label="用户名"/>
    <s:password name="password" label="密码"/>
    <tr align="center">
        <td colspan="2">
        <s:submit value="登录" theme="simple"/>
        <s:reset value="重设" theme="simple"/>
        </td>
    </tr>
</s:form>
</body>
</html>