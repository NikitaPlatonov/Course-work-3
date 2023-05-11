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
                    out.println("Привет! Я жду твой запрос в json-формате");
                    String strJson = in.readLine();
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(strJson).getAsJsonObject();
                    String methodFromRequest = jsonObject.get("type").getAsString();
                    String taskFromRequest = jsonObject.get("task").getAsString();
                    if (methodFromRequest.equals("ADD")) {
                        todos.addTask(taskFromRequest);
                        out.println("Измененный список: ");
                        out.println(todos.getAllTasks());
                    } else if (methodFromRequest.equals("REMOVE")) {
                        todos.removeTask(taskFromRequest);
                        out.println("Измененный список: ");
                        out.println(todos.getAllTasks());
                    } else {
                        out.println("Такой функции нет");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
