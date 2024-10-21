<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品追加</title>
</head>
<body>
	<h1>商品追加</h1>
	<% if (msg != null) { %>
		<%=msg %>
	<% } %>
		<form action="Input" method="post">
		<p>商品ID:<input type="text" name="productId"></p>
		<p>商品名:<input type="text" name="productName"></p>
		<p>商品分類:
			<input type="radio" name="productGroup" value="衣服">衣服 
			<input type="radio" name="productGroup" value="キッチン用品">キッチン用品
			<input type="radio" name="productGroup" value="事務用品">事務用品
		</p>
		<p>販売単価:<input type="text" name="salesPrice"></p>
		<p>仕入単価:<input type="text" name="unitPrice"></p>
		<p><input type="submit" value="登録"></p>
		</form>
		<a href="index.jsp">戻る</a>
</body>
</html>