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
<c:set var="player_name" value="${page.getFilterName()}"/>
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
  Проведено матчей: <c:out value = "${page.getMatchesQuantity()}"/>
</div>
<div class="content">
  <table>
    <tr>
      <th>Игрок #1</th>
      <th>Игрок #2</th>
    </tr>
    <c:forEach var="match" items="${page.getMatches()}">
      <tr>
        <c:set  var="winner" value="${match.getWinner().getId()}"/>
        <c:choose>
          <c:when test="${match.getPlayer(0).getId().equals(winner)}">
            <td class="view">
              <span style="margin-left: 28px"></span>
              <c:out value = "${match.getPlayer(0).getName()}"/>
            <img src="./images/medal.png" height="20" width="20" alt=" (WIN)"/> </td>
          </c:when>
          <c:otherwise>
            <td class="view"><c:out value = "${match.getPlayer(0).getName()}"/>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${match.getPlayer(1).getId().equals(winner)}">
            <td class="view">
              <span style="margin-left: 28px"></span>
              <c:out value = "${match.getPlayer(1).getName()}"/>
              <img src="./images/medal.png" height="20" width="20" alt=" (WIN)"/>
            </td>
          </c:when>
          <c:otherwise>
            <td class="view">
              <c:out value = "${match.getPlayer(1).getName()}"/>
            </td>
          </c:otherwise>
        </c:choose>
      </tr>
    </c:forEach>
    <tr>
      <td class="clean" colspan="3">
        <c:set var="player_name" value="${page.getFilterName()}"/>
        <c:choose>
          <c:when test="${player_name != null && !player_name.isEmpty()}">
            <c:forEach begin="1" end="${page.getPagesQuantity()}" var="page">
              <a href="${pageContext.request.contextPath}/matches?page=${page}&filter_by_player_name=${player_name}">${page}</a>
            </c:forEach></td>
          </c:when>
          <c:otherwise>
            <c:forEach begin="1" end="${page.getPagesQuantity()}" var="ordinal_page">
              <a href="${pageContext.request.contextPath}/matches?page=${ordinal_page}">${ordinal_page}</a>
            </c:forEach>
          </c:otherwise>
        </c:choose>
    </tr>
  </table>
  <br>
</div>
<div class="content">
  <button class="redirect" type="reset" onclick="location.href='${pageContext.request.contextPath}/'">На главную</button>
  <button class="redirect" onclick="location.href='${pageContext.request.contextPath}/new-match'">Добавить матч</button>
</div>
<div class="content">
  <button class="redirect" onclick="location.href='${pageContext.request.contextPath}/matches?page=1'">Показать все матчи</button>
</div>
</body>
</html>
