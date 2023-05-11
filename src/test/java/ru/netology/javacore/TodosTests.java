package ru.netology.javacore;

import org.junit.jupiter.api.*;

public class TodosTests {
    Todos todos;
    @BeforeEach
    void init(){
        todos = new Todos();
        System.out.println("Выполняется перед каждым тестом");
    }
    @Test
    //Этот тестирует то, что метод addTask в классе Todos не будет добавлять никакие задачи после 7-й
    void addTaskMoreSeven(){
        System.out.println("ТЕСТ 1");
        todos.addTask("Первая задача");
        todos.addTask("Вторая задача");
        todos.addTask("Третья задача");
        todos.addTask("Четвертая задача");
        todos.addTask("Пятая задача");
        todos.addTask("Шестая задача");
        todos.addTask("Седьмая задача");
        todos.addTask("Восьмая задача");
        todos.addTask("Девятая задача");
        todos.addTask("Десятая задача");
        String expected = "Вторая задача Первая задача Пятая задача Седьмая задача Третья задача Четвертая задача Шестая задача ";
        Assertions.assertEquals(expected, todos.getAllTasks());
    }
    @Test
    void removeTask(){
        System.out.println("ТЕСТ 2");
        todos.addTask("Акробатика");
        todos.removeTask("Акробатика");
        String expectedTasks = "";
        Assertions.assertEquals(expectedTasks, todos.getAllTasks());
    }
}
