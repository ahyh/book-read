package com.yh.tomcat.read.chapter1;

import java.io.InputStream;

/**
 * 请求类
 *
 * @author yanhuan
 */
public class Request {

    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return "";
    }

    public void parse() {
        StringBuilder sb = new StringBuilder();
        int i;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }
        for (int j = 0; j < i; j++) {
            sb.append((char) buffer[j]);
        }
        System.out.println(sb.toString());
        this.uri = parseUri(sb.toString());
    }

    public String getUri() {
        return uri;
    }

}
