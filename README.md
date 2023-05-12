![GitHub language count](https://img.shields.io/github/languages/count/NikitaPlatonov/Course-work-3) ![GitHub top language](https://img.shields.io/github/languages/top/NikitaPlatonov/Course-work-3)
# Проект "Менеджер задач"
**Менеджер задач** - серверное java-приложение, позволяющее управлять своими задачами.
## Описание
Цель менеджера задач - предоставить клиенту простое управление своими задачами.
## Функционал
* Добавление задачи. Добавление задачи в приложение осуществляется json-запросом:
```json
{
  "type": "ADD",
  "task": "продемонстрировать приложение"
}
```
  > В поле type мы указываем необходимую нам функцию.
  
  > В поле task мы указываем задачу, которую хотим добавить
* Удаление задачи. Удаление задачи в приложении осуществляется json-запросом:
```json
{
  "type": "REMOVE",
  "task": "Здесь указывается задача, которую нужно удалить."
}
```
* Отмена последнего действия. Отмена последнего действия осущесталяется json-запром:
```json
{
  "type": "RESTORE"
}
```
