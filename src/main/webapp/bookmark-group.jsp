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
<h1>북마크 그룹 관리</h1>

<a href="index.jsp">홈</a><a> | </a>
<a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="bookmark-group.jsp">북마크 보기</a><a> | </a>
<a href="/BookmarkGroupServlet">북마크 그룹 관리</a>
<br>
<br>
<button onclick="location.href='bookmark-group-add.jsp'">북마크 그룹 추가</button>

<table>
  <th>ID</th>
  <th>북마크 이름</th>
  <th>순서</th>
  <th>등록일자</th>
  <th>수정일자</th>
  <th>비고</th>
  <%
    List<BookmarkGroup> bookmarkGroupList = (List<BookmarkGroup>) request.getAttribute("bookmarkGroupList");

    StringBuilder sb = new StringBuilder();
    for (int i = bookmarkGroupList.size() - 1 ; i >= 0; i--) {
      BookmarkGroup bookmarkGroup = bookmarkGroupList.get(i);
      sb.append("<tr>")
              .append("<td>" + bookmarkGroup.getId() + "</td>")
              .append("<td>" + bookmarkGroup.getName() + "</td>")
              .append("<td>" + bookmarkGroup.getSortOrder() + "</td>")
              .append("<td>" + bookmarkGroup.getCreatedDate() + "</td>")
              .append("<td>" + bookmarkGroup.getEditedDate() + "</td>")
              .append("<td>" +
                      "<form method='post' action='/BookmarkGroupDeleteServlet'>" +
                      "<input type='hidden' name='id' value='" + bookmarkGroup.getId() + "'/>" +
                      "<input type='submit' value='삭제'/>" +
                      "</form>" +
                      "<form method='get' action='/bookmark-group-edit.jsp'>" +
                      "<input type='hidden' name='id' value='" + bookmarkGroup.getId() + "'/>" +
                      "<input type='hidden' name='bookmarkName' value='" + bookmarkGroup.getName() + "'/>" +
                      "<input type='hidden' name='sortOrder' value='" + bookmarkGroup.getSortOrder() + "'/>" +
                      "<button type='submit'>편집</button>" +
                      "</form>" +
                      "</td>")
              .append("</tr>");
    }
    sb.append("</table>");
    out.println(sb.toString());
  %>
</table>


</body>
</html>