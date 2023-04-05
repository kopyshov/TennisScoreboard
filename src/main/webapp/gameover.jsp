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
      <th>Sets</th>
      <th></th>
    </tr>
    <tr>
      <td>${playerOneName}</td>
      <td>${playerOneSets}</td>
    </tr>
    <tr>
      <td>${playerTwoName}</td>
      <td>${playerTwoSets}</td>
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
