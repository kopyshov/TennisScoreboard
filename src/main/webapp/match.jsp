<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Текущий матч</title>
    <link href="https://allfont.ru/allfont.css?fonts=a_lcdnova" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="main.css">
</head>
<body>
<div>
    <table>
        <tr>
            <td colspan="4">ID матча: ${GameUuid} </td>
        </tr>
        <tr>
            <th>Имя</th>
            <th>Points</th>
            <th>Set 1</th>
            <th>Set 2</th>
            <th></th>
        </tr>
        <tr>
            <td>${playerOneName}</td>
            <td>${playerOnePoints}</td>
            <td>${playerOneSetOne}</td>
            <td>${playerOneSetTwo}</td>
            <td>
                <form method="post">
                    <button name="playerId" value="${playerOneId}">Добавить очко</button>
                </form>
            </td>
        </tr>
        <tr>
            <td>${playerTwoName}</td>
            <td>${playerTwoPoints}</td>
            <td>${playerTwoSetOne}</td>
            <td>${playerTwoSetTwo}</td>
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
