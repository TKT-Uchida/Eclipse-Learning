<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // Servletからのデータの受け取り
    request.setCharacterEncoding("UTF-8");
    String strResult = (String) request.getAttribute("resultStr");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie - 結果</title>
</head>
<body>
    <p>検索結果</p>
    <p><%=strResult %></p>
</body>
</html>