package com.yh.tomcat.read.chapter5.startup;

import com.yh.tomcat.read.chapter5.core.SimpleContext;
import com.yh.tomcat.read.chapter5.core.SimpleContextMapper;
import com.yh.tomcat.read.chapter5.core.SimpleLoader;
import com.yh.tomcat.read.chapter5.core.SimpleWrapper;
import com.yh.tomcat.read.chapter5.valves.ClientIPLoggerValve;
import com.yh.tomcat.read.chapter5.valves.HeaderLoggerValve;
import org.apache.catalina.*;
import org.apache.catalina.connector.http.HttpConnector;

public class Bootstrap2 {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        Wrapper wrapper1 = new SimpleWrapper();
        wrapper1.setName("Primitive");
        wrapper1.setServletClass("PrimitiveServlet");
        Wrapper wrapper2 = new SimpleWrapper();
        wrapper2.setName("Modern");
        wrapper2.setServletClass("ModernServlet");

        Context context = new SimpleContext();
        context.addChild(wrapper1);
        context.addChild(wrapper2);

        Loader loader = new SimpleLoader();
        Valve valve1 = new HeaderLoggerValve();
        Valve valve2 = new ClientIPLoggerValve();

        ((Pipeline) context).addValve(valve1);
        ((Pipeline) context).addValve(valve2);

        Mapper mapper = new SimpleContextMapper();
        mapper.setProtocol("http");
        context.addMapper(mapper);
        context.setLoader(loader);

        context.addServletMapping("/primitive","Primitive");
        context.addServletMapping("/modern","Modern");

        connector.setContainer(context);

        try {
            connector.initialize();
            connector.start();

            // make the application wait until we press a key.
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
