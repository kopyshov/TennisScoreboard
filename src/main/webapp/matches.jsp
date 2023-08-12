<%@ page import="java.util.List" %>
<%@ page import="com.kharizma.tennisscoreboard.matches.Match" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Матчи</title>
  <style>
    <%@ include file="/style.css" %>
  </style>
</head>
<body>
<c:set var="player_name" value="${player_name}"/>
<div class="content">
  <form method="get" name="filter">
    <input type="hidden" name="page" value="1">
    <label>
      <input type="text" placeholder="имя игрока" name="filter_by_player_name">
    </label>
    <button type="submit">Найти</button>
  </form>
</div>
<div class="content">
  <table>
    <tr>
      <th>Игрок #1</th>
      <th>Игрок #2</th>
    </tr>
    <c:forEach var="match" items="${matches}">
      <tr>
        <c:set  var="winner" value="${match.getWinner().getId()}"/>
        <c:choose>
          <c:when test="${match.getPlayerOne().getId().equals(winner)}">
            <td class="view">
              <span style="margin-left: 28px"></span>
              <c:out value = "${match.getPlayerOne().getName()}"/>
            <img src="./images/medal.png" height="20" width="20" alt=" (WIN)"/> </td>
          </c:when>
          <c:otherwise>
            <td class="view"><c:out value = "${match.getPlayerOne().getName()}"/>
          </c:otherwise>
        </c:choose>

        <c:choose>
          <c:when test="${match.getPlayerTwo().getId().equals(winner)}">
            <td class="view">
              <span style="margin-left: 28px"></span>
              <c:out value = "${match.getPlayerTwo().getName()}"/>
              <img src="./images/medal.png" height="20" width="20" alt=" (WIN)"/>
            </td>
          </c:when>
          <c:otherwise>
            <td class="view">
              <c:out value = "${match.getPlayerTwo().getName()}"/>
            </td>
          </c:otherwise>
        </c:choose>
      </tr>
    </c:forEach>
    <tr>
      <td class="clean" colspan="3">
        <%
          String playerName = request.getParameter("filter_by_player_name");
          if (playerName != null && !playerName.isEmpty()) {
        %>
        <c:forEach begin="1" end="${pages}" var="page">
          <a href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${player_name}">${page}</a>
        </c:forEach></td>
        <%
        } else {
        %>
      <c:forEach begin="1" end="${pages}" var="page">
        <a href="${pageContext.request.contextPath}/matches?page=${page}">${page}</a>
      </c:forEach>
        <%
          }
        %>
    </tr>
  </table>
  <br>
</div>
<div class="content">
  <button class="redirect" onclick="location.href='/new-match'">Добавить матч</button>
</div>
<div class="content">
  <button class="redirect" onclick="location.href='/matches?page=1'">Показать все матчи</button>
</div>
</body>
</html>
