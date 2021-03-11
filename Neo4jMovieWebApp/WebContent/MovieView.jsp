<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie - 検索</title>
</head>
<body>
  <form action="<%=request.getContextPath() %>/MovieController" method="post">
    検索文字列を入力して下さい
    <br />
    <input type="text" name="searchVal" value="Keanu Reeves" />
    <br />
    <input type="submit" value="検索" />
  </form>
  <p></p>
  <p>検索候補</p>
  <p>Keanu Reeves</p>
  <p>Nancy Meyers</p>
  <p>Scott Hicks</p>
  <p>Tom Hanks</p>
  <p>Zach Grenier</p>
</body>
</html>