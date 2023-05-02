<%
  Wifi wifi = (Wifi) request.getAttribute("wifi");
%>
<%@ page import="com.example.findadjacentwifi.Wifi" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 상세</title>

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
<h1>와이파이 상세</h1>
<a href="index.jsp">홈</a><a> | </a>
<a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
<a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
<a href="bookmark-view.jsp">북마크 보기</a><a> | </a>
<a href="bookmark-group-manage.jsp">북마크 그룹 관리</a>

<table>
  <tr>
    <th>거리(Km)</th>
    <td><%= String.format("%.5f",wifi.getDistance()) %></td>
  </tr>
  <tr>
    <th>관리번호</th>
    <td><%= wifi.getX_SWIFI_MGR_NO() %></td>
  </tr>
  <tr>
    <th>자치구</th>
    <td><%= wifi.getX_SWIFI_WRDOFC() %></td>
  </tr>
  <tr>
    <th>와이파이명</th>
    <td><a href='DetailServlet?mgr_no=<%= wifi.getX_SWIFI_MGR_NO() %>'><%= wifi.getX_SWIFI_MAIN_NM() %></a></td>
  </tr>
  <tr>
    <th>도로명주소</th>
    <td><%= wifi.getX_SWIFI_ADRES1() %></td>
  </tr>
  <tr>
    <th>상세주소</th>
    <td><%= wifi.getX_SWIFI_ADRES2() %></td>
  </tr>
  <tr>
    <th>설치위치(층)</th>
    <td><%= wifi.getX_SWIFI_INSTL_FLOOR() %></td>
  </tr>
  <tr>
    <th>설치유형</th>
    <td><%= wifi.getX_SWIFI_INSTL_MBY() %></td>
  </tr>
  <tr>
    <th>설치기관</th>
    <td><%= wifi.getX_SWIFI_INSTL_TY() %></td>
  </tr>
  <tr>
    <th>서비스구분</th>
    <td><%= wifi.getX_SWIFI_SVC_SE() %></td>
  </tr>
  <tr>
    <th>망종류</th>
    <td><%= wifi.getX_SWIFI_CMCWR() %></td>
  </tr>
  <tr>
    <th>설치년도</th>
    <td><%= wifi.getX_SWIFI_CNSTC_YEAR() %></td>
  </tr>
  <tr>
    <th>실내외구분</th>
    <td><%= wifi.getX_SWIFI_INOUT_DOOR() %></td>
  </tr>
  <tr>
    <th>wifi접속환경</th>
    <td><%= wifi.getX_SWIFI_REMARS3() %></td>
  <tr>
    <th>LAT</th>
    <td><%= wifi.getLAT() %></td>
  </tr>
  <tr>
    <th>LNT</th>
    <td><%= wifi.getLNT() %></td>
  </tr>
  <tr>
    <th>작업일자</th>
    <td><%= wifi.getWORK_DTTM() %></td>
  </tr>
</table>
</body>
</html>