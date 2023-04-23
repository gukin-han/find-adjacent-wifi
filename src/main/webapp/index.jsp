<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<a href="index.jsp">홈</a>
<a href="history-list.jsp">위치 히스토리 목록</a>
<a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
<a href="bookmark-view.jsp">북마크 보기</a>
<a href="bookmark-group-manage.jsp">북마크 그룹 관리</a>

<form action="/url" method="GET">
  <p>LAT: </p>
  <input type="text" name="lat" placeholder="0.0" required>
  <p>LNT: </p>
  <input type="text" name="lnt" placeholder="0.0" required>
  <button type="submit">내 위치 가져오기</button>
</form>

</body>
</html>