<%@ page import="java.util.List" %>
<%@ page import="com.kharizma.tennisscoreboard.models.Match" %>
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
    <input type="text" placeholder="имя игрока" name="filter_by_player_name">
    <button type="submit">Найти</button>
  </form>
</div>
<div class="content">
  <table>
    <tr>
      <th>First Player</th>
      <th>Second Player</th>
      <th>Winner</th>
    </tr>
    <c:forEach var="match" items="${matches}">
      <tr>
        <td class="view"><c:out value = "${match.getPlayerOne().getName()}"/></td>
        <td class="view"><c:out value = "${match.getPlayerTwo().getName()}"/></td>
        <td class="view"><c:out value = "${match.getWinner().getName()}"/></td>
      </tr>
    </c:forEach>
    <tr>
      <td class="clean" colspan="3">
        <c:forEach begin="1" end="${pages}" var="page">
          <a href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${player_name}">${page}</a>
        </c:forEach></td>
    </tr>
  </table>
  <br>
</div>
<div class="content">
  <button class="redirect" onclick="location.href='/new-match'">Добавить матч</button>
</div>
<div class="content">
  <button class="redirect" onclick="location.href='/matches?page=1&filter_by_player_name='">Показать все матчи</button>
</div>
</body>
</html>
