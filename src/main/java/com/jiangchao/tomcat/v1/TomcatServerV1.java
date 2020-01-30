package com.jiangchao.tomcat.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class TomcatServerV1 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("==============服务启动成功============");
        while (!serverSocket.isClosed()) {
            // 如果有客户端请求服务端，这里会收到socket连接
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            System.out.println("执行客户请求："+Thread.currentThread());
            System.out.println("==========收到客户请求============");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String msg = null;

            // http协议信息
            while ((msg = reader.readLine()) != null) {
                if (msg.length() == 0) {
                    break;
                }
                System.out.println(msg);
            }

            String resp = "OK deer";
            OutputStream outputStream = socket.getOutputStream();
            System.out.println(resp);
            outputStream.write(resp.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    }
}
