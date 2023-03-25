<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Текущий матч</title>
</head>
<body>
<div>Игрок 1</div><button>Выиграл очко</button><br>
<div>Игрок 2</div><button>Выиграл очко</button><br>

<form action="MyServlet" method="post">
    <input type="submit" value="Вызвать метод" name="action">
</form>


</body>
</html>
