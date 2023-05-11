package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTests {
    Todos todos;
    @BeforeEach
    void init(){
        todos = new Todos();
    }
    @Test
    //Этот тестирует то, что метод addTask в классе Todos не будет добавлять никакие задачи после 7-й
    void addTaskMoreSeven(){
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
}
