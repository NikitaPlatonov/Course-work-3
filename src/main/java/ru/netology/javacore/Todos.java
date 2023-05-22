package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final TreeSet<String> tasks = new TreeSet<>(String::compareToIgnoreCase);
    private final List<String> historyOperation = new ArrayList<>();
    private static final int MAXCOUNTASKS = 7;

    public void addTask(String task) {
        if (tasks.size() < MAXCOUNTASKS) {
            tasks.add(task);
            historyOperation.add("ADD " + task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
        historyOperation.add("REMOVE " + task);
    }

    public String getAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        for (String task : tasks) {
            if (allTasks.length() == 0) {
                allTasks.append(task);
            } else {
                allTasks.append(" ").append(task);
            }
        }
        return allTasks.toString();
    }

    public void restoreOperation() {
        List<String> operationList = new ArrayList<>(historyOperation);
        for (int i = operationList.size() - 1; i >= 0; i--) {
            String[] operation = operationList.get(i).split(" ");
            switch (operation[0]) {
                case "ADD":
                    tasks.remove(operation[1]);
                    historyOperation.remove(i);
                    historyOperation.add("RESTORE ADD");
                    break;
                case "REMOVE":
                    tasks.add(operation[1]);
                    historyOperation.remove(i);
                    historyOperation.add("RESTORE REMOVE");
                    break;
            }
        }
    }
}
