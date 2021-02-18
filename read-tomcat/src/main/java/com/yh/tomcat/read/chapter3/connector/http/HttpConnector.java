package com.yh.tomcat.read.chapter3.connector.http;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable {

    private boolean stopped;
    private String scheme = "http";

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
            while (!stopped) {
                Socket socket = serverSocket.accept();
                HttpProcessor processor = new HttpProcessor(this);
                processor.process(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
