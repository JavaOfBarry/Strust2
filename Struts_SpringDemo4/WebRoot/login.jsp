<%@ page contentType="text/html; charset=GBK" language="java" errorPage="" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>��¼ҳ��</title>
</head>
<body>
<h3>�û���¼</h3>
<s:form action="loginPro">
    <s:textfield name="username" label="�û���"/>
    <s:password name="password" label="����"/>
    <tr align="center">
        <td colspan="2">
        <s:submit value="��¼" theme="simple"/>
        <s:reset value="����" theme="simple"/>
        </td>
    </tr>
</s:form>
</body>
</html>