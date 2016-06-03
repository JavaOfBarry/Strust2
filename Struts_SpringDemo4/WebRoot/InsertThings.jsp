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
	<s:form action="insertThings" theme="simple" enctype="multipart/form-data">
		<table align="center" border="1">
			<tr>
				<td>商品名称:</td>
				<td><s:textfield name="name"></s:textfield>
				 <td><s:fielderror fieldName="name"/></td>
				</td>
			</tr>
			<tr>
			<tr>
				<td>商品价格:</td>
				<td><s:textfield name="price"  onkeyup="this.value=this.value.replace(/[^0-9.]+/,'');" ></s:textfield>
				</td>
			</tr>
				<tr>
				<td>商品描述:</td>
				<td><s:textarea name="description" cssStyle="size:10px; width:160px;height:60px"></s:textarea>
				</td>
			</tr>
			<tr>
				<td>商品图片:</td>
				<td id="more"><s:file name="file"></s:file> <input
					type="button" value="Add More.." onclick="addMore()"></td>
			</tr>
			<tr>
				<td></td>
				<td><s:submit value=" submit "></s:submit> <s:reset
						value=" reset "></s:reset></td>

			</tr>
		</table>
	</s:form>

</body>

<script type="text/javascript">
	function addMore() {
		var td = document.getElementById("more");

		var br = document.createElement("br");
		var input = document.createElement("input");
		var button = document.createElement("input");

		input.type = "file";
		input.name = "file";

		button.type = "button";
		button.value = "Remove";

		button.onclick = function() {
			td.removeChild(br);
			td.removeChild(input);
			td.removeChild(button);
		}
		td.appendChild(br);
		td.appendChild(input);
		td.appendChild(button);
	}
</script>
    
  </body>
</html>
