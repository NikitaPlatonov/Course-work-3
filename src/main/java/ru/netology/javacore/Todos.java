package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final Set<String> tasks = new HashSet<>();
    private StringBuilder historyOperation;

    public void addTask(String task) {
        if (tasks.size() < 7) {
            tasks.add(task);
            historyOperation.append("ADD").append(" ").append(task).append("\n");
        }
        getAllTasks();
    }

    public void removeTask(String task) {
        tasks.remove(task);
        historyOperation.append("REMOVE").append(" ").append(task).append("\n");
        getAllTasks();
    }

    public String getAllTasks() {
        List<String> sortedListTasks = new ArrayList<>(tasks);
        sortedListTasks.sort(String::compareToIgnoreCase);
        StringBuilder allTasks = new StringBuilder();
        for (String task : sortedListTasks) {
            if (allTasks.length() == 0) {
                allTasks.append(task);
            } else {
                allTasks.append(" ").append(task);
            }
        }
        return allTasks.toString();
    }

    public void restoreOperation() {
        Deque<String> operations = new ArrayDeque<>(Arrays.asList(historyOperation.toString().split("\n")));
        while (true) {
            String[] operation = operations.getLast().split(" ");
            if (operation[0].equals("ADD")) {
                this.removeTask(operation[1]);
                historyOperation.append("RESTORE").append(" ").append(operation[0]).append("\n");
                this.getAllTasks();
                break;
            }
            if (operation[0].equals("REMOVE")) {
                this.addTask(operation[1]);
                historyOperation.append("RESTORE").append(" ").append(operation[0]).append("\n");
                this.getAllTasks();
                break;
            }
        }
    }
}
