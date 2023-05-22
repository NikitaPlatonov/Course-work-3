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
    private static final String messageToTheClientListTask = "Измененный список задач: ";
    private static final String messageToTheClientNotOperation = "Такой операции нет";
    private static final String nameOperationADD = "ADD";
    private static final String nameOperationREMOVE = "REMOVE";
    private static final String nameOperationRESTORE = "RESTORE";
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
                    String strJson = in.readLine();
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(strJson).getAsJsonObject();
                    String methodFromRequest = jsonObject.get("type").getAsString();
                    String taskFromRequest;
                    if (methodFromRequest.equals(nameOperationRESTORE)) {
                        todos.restoreOperation();
                        out.println(messageToTheClientListTask);
                        out.println(todos.getAllTasks());
                    } else {
                        taskFromRequest = jsonObject.get("task").getAsString();
                        if (methodFromRequest.equals(nameOperationADD)) {
                            todos.addTask(taskFromRequest);
                            out.println(messageToTheClientListTask);
                            out.println(todos.getAllTasks());
                        } else if (methodFromRequest.equals(nameOperationREMOVE)) {
                            todos.removeTask(taskFromRequest);
                            out.println(messageToTheClientListTask);
                            out.println(todos.getAllTasks());
                        } else {
                            out.println(messageToTheClientNotOperation);
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
