<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>위치 히스토리 목록</title>

  <style>
    table {
      width: 100%
    }
    th, td {
      border: solid 1px #100;
      font-size: 12px;
    }
    form {
      padding-bottom: 10px;
      padding-top: 10px;
    }
  </style>

</head>
<body>
<h1>위치 히스토리 목록</h1>

<a href="index.jsp">홈</a><a> | </a>
<a href="history-list.jsp">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="bookmark-view.jsp">북마크 보기</a><a> | </a>
<a href="bookmark-group-manage.jsp">북마크 그룹 관리</a>
<br>
<br>
<table>
  <th>ID</th>
  <th>X좌표</th>
  <th>Y좌표</th>
  <th>조회일자</th>
  <th>비고</th>
</table>


</body>
</html>