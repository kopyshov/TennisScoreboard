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
            <td colspan="5">ID матча: ${match.getId()} </td>
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
            <td class="tablo">${match.getPlayer(0).getName()}</td>
            <td class="tablo">${match.getMatchScore().getCurrentPoints(0)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(0, 0)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(1, 0)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(2, 0)}</td>
            <td class="btn">
                <form method="post">
                    <button class="upBtn" name="playerId" value="${match.getPlayer(0).getCurrentId()}">UP</button>
                </form>
            </td>
        </tr>
        <tr>
            <td class="tablo">${match.getPlayer(1).getName()}</td>
            <td class="tablo">${match.getMatchScore().getCurrentPoints(1)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(0, 1)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(1, 1)}</td>
            <td class="tablo">${match.getMatchScore().getGamesScore(2, 1)}</td>
            <td class="btn">
                <form method="post">
                    <button class="upBtn" name="playerId" value="${match.getPlayer(1).getCurrentId()}">UP</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
