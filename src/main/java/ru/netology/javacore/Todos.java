package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private Set<String> tasks = new HashSet<>();
    public void addTask(String task) {
        if(tasks.size() < 7){
            tasks.add(task);
        }
        getAllTasks();
    }

    public void removeTask(String task) {
        tasks.remove(task);
        getAllTasks();
    }

    public String getAllTasks() {
        List<String> sortedListTasks = new ArrayList<>(tasks);
        sortedListTasks.sort(String::compareToIgnoreCase);
        StringBuilder allTasks = new StringBuilder();
        for(String task : sortedListTasks){
            allTasks.append(task).append(" ");
        }
        return allTasks.toString();
    }

}
