# Проект "табло теннисного матча"

Веб-приложение, реализующее табло счёта теннисного матча.

## Используемые технологии
  - Java сервлеты
  - Веб - GET и POST запросы, формы
  - Java Servlets, JSP
- Базы данных - SQL, JDBC, H2 - in-memory SQL database
- Frontend - HTML/CSS, блочная вёрстка

Комментарии:
- Проект не подразумевает фреймворки, ради практики с паттерном MVC
- Не используем Bootstrap, для практики верстки вручную
- Проект не многопользовательский, поэтому не используем сессии

## Функционал приложения

Работа с матчами:

- Создание нового матча https://github.com/kopyshov/TennisScoreboard/blob/master/src/main/java/com/kharizma/tennisscoreboard/matches/CurrentMatchController.java
- Просмотр законченных матчей, поиск матчей по именам игроков https://github.com/kopyshov/TennisScoreboard/blob/master/src/main/java/com/kharizma/tennisscoreboard/matches/FinishedMatchesPersistenceController.java
- Подсчёт очков в текущем матче https://github.com/kopyshov/TennisScoreboard/tree/master/src/main/java/com/kharizma/tennisscoreboard/matches/score

## Подсчёт очков в теннисном матче
В теннисе особая система подсчёта очков - https://www.gotennis.ru/read/world_of_tennis/pravila.html

Для упрощения, допустим что каждый матч играется по следующим правилам:
- Матч играется до двух сетов (best of 3)
- При счёте 6/6 в сете, играется тай-брейк до 7 очков
