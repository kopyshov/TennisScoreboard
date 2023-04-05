<%@ page import="java.util.List" %>
<%@ page import="com.kharizma.tennisscoreboard.models.Match" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Матчи</title>
</head>
<body>
<c:set var="player_name" value="${player_name}"/>
<div>
  <form method="get" name="filter">
    <input type="hidden" name="page" value="1">
    <input type="text" placeholder="имя игрока" name="filter_by_player_name">
    <button type="submit">Найти</button>
  </form>
</div>
<div>
  <table>
    <tr>
      <th>First Player</th>
      <th>Second Player</th>
      <th>Winner</th>
    </tr>
    <c:forEach var="match" items="${matches}">
      <tr>
        <td><c:out value = "${match.getPlayerOne().getName()}"/></td>
        <td><c:out value = "${match.getPlayerTwo().getName()}"/></td>
        <td><c:out value = "${match.getWinner().getName()}"/></td>
      </tr>
    </c:forEach>
  </table>
  <br>
    <c:forEach begin="1" end="${pages}" var="page">
      <a href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${player_name}">${page}</a>
    </c:forEach>
</div>
<div>
  <button onclick="location.href='/new-match'">Добавить матч</button>
</div>
<div>
  <button onclick="location.href='/matches?page=1&filter_by_player_name='">Показать все матчи</button>
</div>
</body>
</html>
