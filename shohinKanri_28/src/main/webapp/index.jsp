<%@page import="model.Shohin"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
List<Shohin> shohinList = (List<Shohin>)session.getAttribute("shohinList");
String msg = (String)request.getAttribute("msg");
String findShohinName = (String)session.getAttribute("findShohinName");
String findShohinGroup = (String)session.getAttribute("findShohinGroup");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
	<h1>商品検索</h1>
	<% if (msg != null) { %>
		<p><%=msg %></p>
	<% } %>
	<form action="Search" method="post">
		<table border="1">
			<tr>
				<th>商品名</th>
				<th><input type="text" name="productName" <% if (findShohinName != null && findShohinName.length() != 0) { %>value="<%=findShohinName%>"<%} %>></th>
				<th>商品分類</th>
				<th><select name="productGroup">
					<option value="0"<% if (findShohinGroup == null || findShohinGroup.length() == 0) {%> selected  <% } %>></option>
					<option value="1"<% if (findShohinGroup != null && findShohinGroup.equals("衣服")) { %>selected <%} %>>衣服</option>
					<option value="2"<% if (findShohinGroup != null && findShohinGroup.equals("キッチン用品")) { %>selected <%} %>>キッチン用品</option>
					<option value="3"<% if (findShohinGroup != null && findShohinGroup.equals("事務用品")) { %>selected <%} %>>事務用品</option>
				</select></th>
			</tr>
		</table>
		<input type="submit" value="検索">
	</form>
	
	
	<form action="Split" method="post">
	<table border="1">
		<tr>
			<th>選択</th>
			<th>商品ID</th>
			<th>商品名</th>
			<th>商品分類</th>
			<th>販売単価</th>
			<th>仕入単価</th>
		</tr>
		<% if(shohinList != null) { %>
			<% for (Shohin s : shohinList) { %>
			<tr>
				<td><input type="radio" name="shohinIndex" value="<%=shohinList.indexOf(s) %>"></td>
				<td><%=s.getProductId() %></td>
				<td><%=s.getProductName() %></td>
				<td><%=s.getProductGroup() %></td>
				<td><%=s.getSalesPrice() %></td>
				<td><%=s.getUnitPrice() %></td>
			</tr>
			<% } %>
		<% } %>
	</table>
	<a href="Input">追加</a>
	<% if (shohinList != null) { %> 
	<input type="submit" name="submit" value="更新"> 
	<input type="submit" name="submit" value="削除"> 
	<% } %>
	<input type="submit" name="submit" value="CSV全件出力">
	</form>
</body>
</html>