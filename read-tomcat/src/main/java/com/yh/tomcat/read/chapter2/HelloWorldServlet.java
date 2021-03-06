package com.yh.tomcat.read.chapter2;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 原生Servlet
 *
 * @author yanhuan
 */
public class HelloWorldServlet implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service");
        PrintWriter writer = servletResponse.getWriter();
        String headerMessage = "HTTP/1.1 200 OK\r\n" +
                "Content-Type:text/html\r\n" +
                "\r\n";
        writer.write(headerMessage);
        writer.write("Hello, servlet");
        writer.println("Goodbye, servlet");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
