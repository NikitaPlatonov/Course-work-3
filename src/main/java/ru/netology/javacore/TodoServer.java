package ru.netology.javacore;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int port;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            while (true) {
                try (Socket socket = serverSocket.accept(); BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream());) {
                    out.println("Привет! Я жду твой запрос в json-формате");
                    while (true){
                        String strJson = in.readLine();
                        JsonParser parser = new JsonParser();
                        JsonObject jsonObject = parser.parse(strJson).getAsJsonObject();
                        String method = jsonObject.get("type").getAsString();
                        String task = jsonObject.get("task").getAsString();
                        if(method.equals("addTask")){
                            todos.addTask(task);
                            out.println("Измененный список: ");
                            out.println(todos.getAllTasks());
                        } else if(method.equals("removeTask")){
                            todos.removeTask(task);
                            out.println("Измененный список: ");
                            out.println(todos.getAllTasks());
                        } else{
                            out.println("Такой функции нет");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
    }
}
