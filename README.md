Задача:  
Есть 2 сущности: Автор и Книга.  
Необходимо продумать и реализовать веб-сервер, поддерживающий базу книг и их авторов.   
Web-интерфейс реализовывать не обязательно - достаточно Web API .

Сборку приложения выполнять с помошью maven lifecycle.  
Запуск веб приложения выполнять из класса Application и сервер будет по пути localhost:8080.  
Проверку приложения можно выполнить с помощью следующих запросов:  
- Регистрация нового пользователя производится предачей на /register в метод POST  
  параметры {"name", "password", "password2"}  
- Логин передачей на /login в метод POST параметров username, password  
- Создание книги передачей книги на /rest/books/ в метод POST  
- Изменение книги передачей измененной книги на /rest/books/ в метод PUT  
- Поиск книг по символам абв передачей /rest/books/byPatter/абв в метод GET  
- Поиск книг по автору передачей /rest/books/byAuthors/name в метод GET  
- Неавторизованные пользователи не могут попасть на rest/authors/ и rest/books/  
- Только администраторы могут добавлять и обновлять книги  
  
--  
**Ibragimov**  
Тестовое задание  