<%@ page import="com.example.findadjacentwifi.domain.History" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.findadjacentwifi.domain.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>북마크 그룹 관리</title>

  <style>
    table {
      width: 100%;

    }
    th, td {
      border: solid 1px #100;
      font-size: 12px;
    }
    th {
      background-color: aquamarine;
    }

    form {
      padding-bottom: 10px;
      padding-top: 10px;
    }
  </style>

</head>
<body>
<h1>북마크 그룹 관리 - 추가</h1>

<a href="index.jsp">홈</a><a> | </a>
<a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="bookmark-group.jsp">북마크 보기</a><a> | </a>
<a href="/BookmarkGroupServlet">북마크 그룹 관리</a>
<br>
<br>
<form action="/BookmarkGroupAddServlet" method="POST">
  <table>
    <tr>
      <th>북마크 이름</th>
      <td><input type="text" name="bookmarkName"></td>
    </tr>
    <tr>
      <th>순서</th>
      <td><input type="text" name="sortOrder"></td>
    </tr>
  </table>
  <button type="submit">추가</button>
</form>
</body>
</html>