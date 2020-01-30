package com.jiangchao.tomcat.v3;

import com.jiangchao.tomcat.servlet.HttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class TomcatServerV3 {

    public static final HashMap<String, HttpServlet> servletMapping = new HashMap<>();

    public static final HashMap<String, String> urlMapping = new HashMap<>();

    public static void main(String[] args) {
        TomcatServerV3 tomcatServerV3 = new TomcatServerV3();
        tomcatServerV3.init();
        tomcatServerV3.run();

    }

    // 初始化，加载web.xml里面配置的servlet信息
    private void init() {
        try {
            String path = TomcatServerV3.class.getResource("/").getPath();
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(path + "web.xml"));
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();

            for (Element element : elements) {
                if ("servlet".equalsIgnoreCase(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");
                    System.out.println(servletName.getText() + "=======>" + servletClass.getText());

                    servletMapping.put(servletName.getText(), (HttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    Element servletName = element.element("servlet-name");
                    Element urlpattern = element.element("url-pattern");
                    System.out.println(servletName.getText() + "===>" + urlpattern.getText());

                    urlMapping.put(urlpattern.getText(), servletName.getText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("==============服务启动成功============");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                new Thread(requestHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
