<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление матча</title>
    <style>
        <%@ include file="/style.css" %>
    </style>
</head>
<body>
<h1 class="content"><%= "Add new match" %></h1>
<div class="content">
    <form method="post">
        <label>Имя Игрока 1:
            <input type="text" name="name1" required title="Введите имя игрока"><br />
        </label>
        <label>Имя Игрока 2:
            <input type="text" name="name2" required title="Введите имя игрока"><br />
        </label>
        <p class="errorMessage"><с:out value="${error_message.message()}"/></p>
        <button class="redirect" type="reset" onclick="location.href='${pageContext.request.contextPath}/'">На главную</button>
        <button class="redirect" type="submit">Начать</button>
    </form>
</div>
</body>
</html>
