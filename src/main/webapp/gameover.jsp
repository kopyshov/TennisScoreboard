<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title>Текущий матч</title>
  <style>
    <%@ include file="/style.css" %>
  </style>
</head>
<body>
<div class="content">
  <table>
    <tr>
      <td colspan="5">ID матча: ${match.getId()} </td>
    </tr>
    <tr>
      <th>Имя</th>
      <th>Set #1</th>
      <th>Set #2</th>
      <th>Set #3</th>
      <th></th>
    </tr>
    <tr>
      <td class="tablo">${match.getPlayer(0).getName()}
        <c:if test="${match.getPlayer(0).getCurrentId().equals(match.getWinner().getCurrentId())}"><img src="images/medal.png" height="20" width="20" alt=""></c:if>
      </td>
      <td class="tablo">${match.getMatchScore().getGamesScore(0, 0)}</td>
      <td class="tablo">${match.getMatchScore().getGamesScore(1, 0)}</td>
      <td class="tablo">${match.getMatchScore().getGamesScore(2, 0)}</td>
    </tr>
    <tr>
      <td class="tablo">${match.getPlayer(1).getName()}
        <c:if test="${match.getPlayer(1).getCurrentId().equals(match.getWinner().getCurrentId())}"><img src="images/medal.png" height="20" width="20" alt=""></c:if>
      </td>
      <td class="tablo">${match.getMatchScore().getGamesScore(0, 1)}</td>
      <td class="tablo">${match.getMatchScore().getGamesScore(1, 1)}</td>
      <td class="tablo">${match.getMatchScore().getGamesScore(2, 1)}</td>
    </tr>
    <tr>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/matches?match-uuid=${match.getId()}">
          <button class="upBtn">Сохранить результат</button>
        </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
