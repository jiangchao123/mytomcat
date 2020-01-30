package com.jiangchao.tomcat.v2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class TomcatServerV2 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("==============服务启动成功============");
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            RequestHandler requestHandler = new RequestHandler(socket);
            new Thread(requestHandler).start();
        }
    }
}
