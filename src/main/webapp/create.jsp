<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление матча</title>
</head>
<body>
<h1><%= "Add new match" %></h1>
<div>
    <form method="post">
        <label>Имя Игрока 1:
            <input type="text" name="name1"><br />
        </label>
        <label>Имя Игрока 2:
            <input type="text" name="name2"><br />
        </label>

        <button type="submit">Начать</button>
    </form>
</div>
<div>
    <button onclick="location.href='/'">Назад</button>
</div>
</body>
</html>
