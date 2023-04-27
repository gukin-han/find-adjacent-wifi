<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
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
    <h1>와이파이 정보 구하기</h1>

    <a href="index.jsp">홈</a><a> | </a>
    <a href="history-list.jsp">위치 히스토리 목록</a><a> | </a>
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a><a> | </a>
    <a href="bookmark-view.jsp">북마크 보기</a><a> | </a>
    <a href="bookmark-group-manage.jsp">북마크 그룹 관리</a>

    <form method="post">
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
    </table>

    <script>
      function fillInputFields() {
        const lat = document.getElementById("lat");
        const lnt = document.getElementById("lnt");
        lat.value = "37.5028043";
        lnt.value = "127.0413466";
      }
    </script>

    <%
      if(request.getParameter("submitBtn") != null) {
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        // Java code to be executed when button is clicked
        out.println("You entered: " + lat + lnt);
      }
    %>

  </body>
</html>