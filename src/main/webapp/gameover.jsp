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
      <th>Set One</th>
      <th>Set Two</th>
      <th>Set Three</th>
      <th></th>
    </tr>
    <tr>
      <td class="tablo">${playerOneName}</td>
      <td class="tablo">${playerOneSetOne}</td>
      <td class="tablo">${playerOneSetTwo}</td>
      <td class="tablo">${playerOneSetThree}</td>
    </tr>
    <tr>
      <td class="tablo">${playerTwoName}</td>
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
