package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final Set<String> tasks = new HashSet<>();
    private final List<String> historyOperation = new ArrayList<>();

    public void addTask(String task) {
        if (tasks.size() < 7) {
            tasks.add(task);
            historyOperation.add("ADD " + task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
        historyOperation.add("REMOVE " + task);
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
        List<String> operationList = new ArrayList<>(historyOperation);
        for(int i = 0;i<operationList.size();i++){
            String[] operation = operationList.get(i).split(" ");
            if(operation[0].equals("ADD")){
                removeTask(operation[1]);
                historyOperation.remove(i);
                historyOperation.add("RESTORE ADD");
                break;
            }
            if(operation[0].equals("REMOVE")){
                addTask(operation[1]);
                historyOperation.remove(i);
                historyOperation.add("RESTORE REMOVE");
                break;
            }
        }
    }
}
