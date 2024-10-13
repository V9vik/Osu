package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;




        public class Main {
            public static void main(String[] args) {
                int port = 8080;

                try (ServerSocket serverSocket = new ServerSocket(port)) {
                    System.out.println("Сервер запущен. Ожидание подключения...");

                    while (true) {
                        try (Socket clientSocket = serverSocket.accept()) {
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                            System.out.println("Новое соединение!");

                            final String name = in.readLine();
                            out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                        } catch (IOException e) {
                            System.err.println("Ошибка при обработке клиента: " + e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Ошибка при запуске сервера: " + e.getMessage());
                }
            }
        }