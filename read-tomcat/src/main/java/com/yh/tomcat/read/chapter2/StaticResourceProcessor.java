package com.yh.tomcat.read.chapter2;

public class StaticResourceProcessor {

    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
