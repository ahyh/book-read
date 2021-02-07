package com.yh.tomcat.read.chapter1;

import com.yh.tomcat.read.constants.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 响应类
 *
 * @author yanhuan
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;
    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws Exception {
        byte[] buffer = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(Constants.WEB_ROOT1, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
                // 此处需要加请求头, 否则无法解析
                String headerMessage = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type:text/html\r\n" +
                        "\r\n";
                outputStream.write(headerMessage.getBytes());
                int ch = fis.read(buffer, 0, BUFFER_SIZE);
                while (ch != -1) {
                    outputStream.write(buffer, 0, ch);
                    ch = fis.read(buffer, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "File Not Found";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

}
