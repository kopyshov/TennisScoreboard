<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
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
            <th>Points</th>
            <th>Set #1</th>
            <th>Set #2</th>
            <th>Set #3</th>
            <th></th>
        </tr>
        <tr>
            <td class="tablo">${playerOneName}</td>
            <td class="tablo">${playerOnePoints}</td>
            <td class="tablo">${playerOneSetOne}</td>
            <td class="tablo">${playerOneSetTwo}</td>
            <td class="tablo">${playerOneSetThree}</td>
            <td class="btn">
                <form method="post">
                    <button class="upBtn" name="playerId" value="${playerOneId}">UP</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="tablo">${playerTwoName}</td>
            <td class="tablo">${playerTwoPoints}</td>
            <td class="tablo">${playerTwoSetOne}</td>
            <td class="tablo">${playerTwoSetTwo}</td>
            <td class="tablo">${playerTwoSetThree}</td>
            <td class="btn">
                <form method="post">
                    <button class="upBtn" name="playerId" value="${playerTwoId}">UP</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
