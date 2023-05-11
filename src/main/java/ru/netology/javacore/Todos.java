package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private static final int SIZE_TODOLIST_ARRAY = 7;
    private static Set<String> tasks = new HashSet<>();
    public void addTask(String task) {
        if(tasks.size() < 8 && task != null){
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
        Collections.sort(sortedListTasks);
        StringBuilder allTasks = new StringBuilder();
        for(String task : sortedListTasks){
            allTasks.append(task).append(" ");
        }
        return allTasks.toString();
    }

}
