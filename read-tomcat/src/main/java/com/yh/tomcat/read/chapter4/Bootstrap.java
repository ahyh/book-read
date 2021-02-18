package com.yh.tomcat.read.chapter4;

import org.apache.catalina.connector.http.HttpConnector;

public final class Bootstrap {

    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        SimpleContainer simpleContainer = new SimpleContainer();
        httpConnector.setContainer(simpleContainer);
        try {
            httpConnector.initialize();
            httpConnector.start();

            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
