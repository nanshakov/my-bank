# Quickfixj and spring-boot application example
## Что это и для чего ?
Проект предоставляет удобный шаблон для создания spring-boot приложений с библиотекой Quickfixj.
Spring-boot позволяет удобно добавлять дургие Spring - зависимости и развивать проект юыстрее.
В этом проекте реализовал stub для банка, обрабатывающий запросы на groovy
## Как это работает ?
В проекте есть несколько сервисов:
1. QFServiceImpl - реализация стандартного fix приложения
2. SimpleOrderProcessor - реализация обработки ордеров
Так же есть несколько обработчиков в папке ```processing```. Логика обработки такая:
(Когда приходит запрос на обработку)
1. Получить список файлов из папки ```processing```
2. Скомпилировать файлы one-by-one и вызывая метод ```isApplying``` - найти подходящий. 
Вызвать метод result
## Что можно доработать
1. Добавить разбор fix сообщений 
2. возвращать в ответ из groovy либо dto либо fix сообщение (как удобнее)