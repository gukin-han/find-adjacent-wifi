<%@ page import="java.util.List" %>
<%@ page import="com.example.findadjacentwifi.repository.DBHandler" %>
<%@ page import="com.example.findadjacentwifi.repository.JdbcBookmarkGroupRepository" %>
<%@ page import="com.example.findadjacentwifi.domain.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>북마크 보기</title>

  <style>
    table {
      width: 100%;

    }
    th, td {
      border: solid 1px #100;
      font-size: 20px;
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
<h1>북마크 보기</h1>

<a href="index.jsp">홈</a><a> | </a>
<a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="/BookmarkViewServlet">북마크 보기</a><a> | </a>
<a href="/BookmarkGroupServlet">북마크 그룹 관리</a>
<br>
<br>

<table>
  <th>ID</th>
  <th>북마크 이름</th>
  <th>와이파이명</th>
  <th>등록일자</th>
  <th>비고</th>

  <%

    List<BookmarkJoined> bookmarkList = (List<BookmarkJoined>) request.getAttribute("bookmarkList");
    if (bookmarkList != null) {
      StringBuilder sb = new StringBuilder();
      for (int i = bookmarkList.size() - 1 ; i >= 0; i--) {

        BookmarkJoined bookmark = bookmarkList.get(i);
        sb.append("<tr>")
                .append("<td>" + bookmark.getId() + "</td>")
                .append("<td>" + bookmark.getBookmarkGroupName() + "</td>")
                .append("<td><a href='/DetailServlet?mgr_no=" + bookmark.getWifiId() + "'>" + bookmark.getWifiName() + "</a></td>")
                .append("<td>" + bookmark.getCreatedDate() + "</td>")
                .append("<td>" +
                        "<form method='post' action='/BookmarkDeleteServlet'>" +
                        "<input type='hidden' name='id' value='" + bookmark.getId() + "'/>" +
                        "<input type='submit' value='삭제'/>" +
                        "</form>" +
                        "</td>")
                .append("</tr>");
      }
      sb.append("</table>");
      out.println(sb.toString());
    }

  %>
</table>


</body>
</html>