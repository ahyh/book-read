package com.yh.tomcat.read.chapter2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer1 {

    public static final String SHUTDOWN_CMD = "shutdown";
    private static final int port = 8080;

    private boolean shutdown = false;

    public static void main(String[] args) throws Exception {
        HttpServer1 httpServer1 = new HttpServer1();
        httpServer1.await();
    }

    public void await() throws Exception {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(port, 1, InetAddress.getByName("localhost"));

        while (!shutdown) {

            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor1 processor = new ServletProcessor1();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();
                    staticResourceProcessor.process(request, response);
                }

                socket.close();
                shutdown = request.getUri().equals(SHUTDOWN_CMD);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
