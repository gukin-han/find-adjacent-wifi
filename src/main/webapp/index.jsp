<%@ page import="com.example.findadjacentwifi.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
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
    <h1>와이파이 정보 구하기</h1>

    <a href="index.jsp">홈</a><a> | </a>
    <a href="/HistoryListServlet">위치 히스토리 목록</a><a> | </a>
    <a href="/LoadAllWifiOnDBServlet">Open API 와이파이 정보 가져오기</a><a> | </a>
    <a href="bookmark-view.jsp">북마크 보기</a><a> | </a>
    <a href="bookmark-group-manage.jsp">북마크 그룹 관리</a>

    <form method="post" action="/DistanceCalculatorServlet">
      LAT: <input type="text" name="lat" id="lat">
      LNT: <input type="text" name="lnt" id="lnt">
      <input type="button" value="내 위치 가져오기" onclick="fillInputFields()">
      <input type="submit" value="근처 wifi 정보 보기" name="submitBtn">
    </form>


    <table>
      <th>거리(Km)</th>
      <th>관리번호</th>
      <th>자치구</th>
      <th>와이파이명</th>
      <th>도로명주소</th>
      <th>상세주소</th>
      <th>설치위치(층)</th>
      <th>설치유형</th>
      <th>설치기관</th>
      <th>서비스구분</th>
      <th>망종류</th>
      <th>설치년도</th>
      <th>실내외구분</th>
      <th>wifi접속환경</th>
      <th>LAT</th>
      <th>LNT</th>
      <th>작업일자</th>
    <%
      String latParam = request.getParameter("lat");
      String lntParam = request.getParameter("lnt");
      List<Wifi> nearestWifiList = (List<Wifi>) request.getAttribute("nearestWifi");

      if (latParam == null || lntParam == null || nearestWifiList == null) {
          out.println("<tr>");
          out.println("<td colspan = 17>");
          out.println("<p>You need to enter latitude and longitude values.</p>");
          out.println("</td>");
          out.println("<tr>");
      } else {
        StringBuilder sb = new StringBuilder();
          for (int i = nearestWifiList.size() - 1 ; i >= 0; i--) {
            Wifi wifi = nearestWifiList.get(i);
              sb.append("<tr>")
              .append("<td>" + String.format("%.5f",wifi.getDistance()) + "</td>")
              .append("<td>" + wifi.getX_SWIFI_MGR_NO() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_WRDOFC() + "</td>")
              .append("<td><a href='/DetailServlet?mgr_no=" + wifi.getX_SWIFI_MGR_NO() + "'>" + wifi.getX_SWIFI_MAIN_NM() + "</a></td>")
              .append("<td>" + wifi.getX_SWIFI_ADRES1() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_ADRES2() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_INSTL_FLOOR() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_INSTL_MBY() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_INSTL_TY() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_SVC_SE() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_CMCWR() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_CNSTC_YEAR() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_INOUT_DOOR() + "</td>")
              .append("<td>" + wifi.getX_SWIFI_REMARS3() + "</td>")
              .append("<td>" + wifi.getLAT() + "</td>")
              .append("<td>" + wifi.getLNT() + "</td>")
              .append("<td>" + wifi.getWORK_DTTM() + "</td>")
              .append("</tr>");
          }
          sb.append("</table>");
          out.println(sb.toString());
      }
    %>
    </table>
<%--    더미 경도와 위도를 로드   --%>
    <script>
      function fillInputFields() {
        const lat = document.getElementById("lat");
        const lnt = document.getElementById("lnt");
        lat.value = "37.5028043";
        lnt.value = "127.0413466";
      }
    </script>

  </body>
</html>