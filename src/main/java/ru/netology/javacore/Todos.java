package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private static final int SIZE_TODOLIST_ARRAY = 7;
    private static String[] tasks = new String[SIZE_TODOLIST_ARRAY];
    public void addTask(String task) {
        for(int i = 0; i < tasks.length;i++){
            if (tasks[i] == null) {
                tasks[i] = task;
                break;
            }
        }
    }

    public void removeTask(String task) {
        for(int i = 0; i < tasks.length;i++){
            if (tasks[i].equals(task)) {
                tasks[i] = null;
                break;
            }
        }
    }

    public String getAllTasks() {
        List<String> sortedListTasks = new ArrayList<>();
        for(int i = 0; i < tasks.length;i++){
            if(tasks[i] != null){
                sortedListTasks.add(tasks[i]);
            }
        }
        Collections.sort(sortedListTasks);
        StringBuilder allTasks = new StringBuilder();
        for(String task : sortedListTasks){
            allTasks.append(task).append(" ");
        }
        return allTasks.toString();
    }

}
