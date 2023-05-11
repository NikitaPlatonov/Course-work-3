package ru.netology.javacore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private static final String messageToTheClient_greeting = "Привет! Я жду твой запрос в json-формате";
    private static final String messageToTheClient_ListTask = "Измененный список задач: ";
    private static final String messageToTheClient_NotOperation = "Такой операции нет";
    private static final String nameOperation_ADD = "ADD";
    private static final String nameOperation_REMOVE = "REMOVE";
    private static final String nameOperation_RESTORE = "RESTORE";
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен");
            while (true) {
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    System.out.println("Подключился клиент с таким портом: " + socket.getPort());
                    out.println(messageToTheClient_greeting);
                    String strJson = in.readLine();
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(strJson).getAsJsonObject();
                    String methodFromRequest = jsonObject.get("type").getAsString();
                    String taskFromRequest;
                    if (methodFromRequest.equals(nameOperation_RESTORE)) {
                        todos.restoreOperation();
                        out.println(messageToTheClient_ListTask);
                        out.println(todos.getAllTasks());
                    } else {
                        taskFromRequest = jsonObject.get("task").getAsString();
                        if (methodFromRequest.equals(nameOperation_ADD)) {
                            todos.addTask(taskFromRequest);
                            out.println(messageToTheClient_ListTask);
                            out.println(todos.getAllTasks());
                        } else if (methodFromRequest.equals(nameOperation_REMOVE)) {
                            todos.removeTask(taskFromRequest);
                            out.println(messageToTheClient_ListTask);
                            out.println(todos.getAllTasks());
                        } else {
                            out.println(messageToTheClient_NotOperation);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
