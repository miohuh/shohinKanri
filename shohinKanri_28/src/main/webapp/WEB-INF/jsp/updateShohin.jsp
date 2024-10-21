<%@page import="model.Shohin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Shohin shohin = (Shohin)session.getAttribute("shohin");
String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品更新</title>
</head>
<body>
	<h1>商品更新</h1>
		<% if (msg != null) { %>
			<p><%=msg %></p>
		<% } %>
		<form action="Update" method="post">
		<p>商品ID:<input type="text" name="updateId" value="<%=shohin.getProductId() %>" readonly></p>
		<p>商品名:<input type="text" name="updateName" value="<%=shohin.getProductName() %>"></p>
		<p>商品分類:
			<input type="radio" name="updateGroup" value="衣服" 
				<% if (shohin.getProductGroup().equals("衣服")) { %> checked <% } %> >衣服 
			<input type="radio" name="updateGroup" value="キッチン用品"
				<% if (shohin.getProductGroup().equals("キッチン用品")) { %> checked <% } %>>キッチン用品
			<input type="radio" name="updateGroup" value="事務用品"
				<% if (shohin.getProductGroup().equals("事務用品")) { %> checked <% } %>>事務用品
		</p>
		<p>販売単価:<input type="text" name="updateSalesPrice" value="<%=shohin.getSalesPrice() %>"></p>
		<p>仕入単価:<input type="text" name="updateUnitPrice" value="<%=shohin.getUnitPrice() %>"></p>
		<p><input type="submit" value="更新"></p>
		</form>
		<a href="index.jsp">戻る</a>
</body>
</html>