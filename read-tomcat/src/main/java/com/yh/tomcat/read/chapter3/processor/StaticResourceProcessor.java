package com.yh.tomcat.read.chapter3.processor;


import com.yh.tomcat.read.chapter3.connector.http.HttpRequest;
import com.yh.tomcat.read.chapter3.connector.http.HttpResponse;

import java.io.IOException;

public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
