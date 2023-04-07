<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
  <title>Текущий матч</title>
</head>
<body>
<div>
  <table>
    <tr>
      <td colspan="4">ID матча: ${GameUuid} </td>
    </tr>
    <tr>
      <th>Имя</th>
      <th>Set One</th>
      <th>Set Two</th>
      <th></th>
    </tr>
    <tr>
      <td>${playerOneName}</td>
      <td>${playerOneSetOne}</td>
      <td>${playerOneSetTwo}</td>
    </tr>
    <tr>
      <td>${playerTwoName}</td>
      <td>${playerTwoSetOne}</td>
      <td>${playerTwoSetTwo}</td>
    </tr>
    <tr>
      <td>
        <form method="post" action="${pageContext.request.contextPath}/matches?match-uuid=${GameUuid}">
          <button>Сохранить результат</button>
        </form>
      </td>
    </tr>
  </table>
</div>
</body>
</html>
