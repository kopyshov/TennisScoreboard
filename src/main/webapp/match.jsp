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
            <th>Games</th>
            <th>Points</th>
            <th></th>
        </tr>
        <tr>
            <td>${playerOneName}</td>
            <td>${playerOneSets}</td>
            <td>${playerOneGames}</td>
            <td>${playerOnePoints}</td>
            <td>
                <form method="post">
                    <button name="playerId" value="${playerOneId}">Добавить очко</button>
                </form>
            </td>
        </tr>
        <tr>
            <td>${playerTwoName}</td>
            <td>${playerTwoSets}</td>
            <td>${playerTwoGames}</td>
            <td>${playerTwoPoints}</td>
            <td>
                <form method="post">
                    <button name="playerId" value="${playerTwoId}">Добавить очко</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
