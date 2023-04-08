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
            <input type="text" name="name1"><br />
        </label>
        <label>Имя Игрока 2:
            <input type="text" name="name2"><br />
        </label>
        <button class="redirect" onclick="location.href='/'">Назад</button>
        <button class="redirect" type="submit">Начать</button>
    </form>
</div>
<div class="content">

</div>
</body>
</html>
