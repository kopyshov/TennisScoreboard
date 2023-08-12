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
      <td colspan="5">ID матча: ${GameUuid} </td>
    </tr>
    <tr>
      <th>Имя</th>
      <th>Set #1</th>
      <th>Set #2</th>
      <th>Set #3</th>
      <th></th>
    </tr>
    <tr>
      <td class="tablo">${playerOneName}
        <c:if test="${playerOneId.equals(winnerId)}"><img src="images/medal.png" height="20" width="20" alt=""></c:if>
      </td>
      <td class="tablo">${playerOneSetOne}</td>
      <td class="tablo">${playerOneSetTwo}</td>
      <td class="tablo">${playerOneSetThree}</td>
    </tr>
    <tr>
      <td class="tablo">${playerTwoName}
        <c:if test="${playerTwoId.equals(winnerId)}"><img src="images/medal.png" height="20" width="20" alt=""></c:if>
      </td>
      <td class="tablo">${playerTwoSetOne}</td>
      <td class="tablo">${playerTwoSetTwo}</td>
      <td class="tablo">${playerTwoSetThree}</td>
    </tr>
    <tr>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/matches?match-uuid=${GameUuid}">
          <button class="upBtn">Сохранить результат</button>
        </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
