<%@ page import="com.example.findadjacentwifi.domain.History" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>위치 히스토리 목록</title>

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
<h1>위치 히스토리 목록</h1>

<a href="index.jsp">홈</a><a> | </a>
<a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="/BookmarkViewServlet">북마크 보기</a><a> | </a>
<a href="/BookmarkGroupServlet">북마크 그룹 관리</a>
<br>
<br>
<table>
  <th>ID</th>
  <th>Latitude</th>
  <th>Longitude</th>
  <th>조회일자</th>
  <th>비고</th>
  <%
    List<History> historyList = (List<History>) request.getAttribute("historyList");

    StringBuilder sb = new StringBuilder();
    for (int i = historyList.size() - 1 ; i >= 0; i--) {
      History history = historyList.get(i);
      sb.append("<tr>")
              .append("<td>" + history.getId() + "</td>")
              .append("<td>" + history.getLat() + "</td>")
              .append("<td>" + history.getLnt() + "</td>")
              .append("<td>" + history.getLookupDate() + "</td>")
              .append("<td><form method='post' action='/DeleteHistoryServlet'><input type='hidden' name='id' value='" + history.getId() + "'/><input type='submit' value='삭제'/></form></td>")
              .append("</tr>");
    }
    sb.append("</table>");
    out.println(sb.toString());
  %>
</table>


</body>
</html>