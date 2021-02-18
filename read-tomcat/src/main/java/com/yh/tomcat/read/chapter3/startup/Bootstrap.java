package com.yh.tomcat.read.chapter3.startup;

import com.yh.tomcat.read.chapter3.connector.http.HttpConnector;

public class Bootstrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
