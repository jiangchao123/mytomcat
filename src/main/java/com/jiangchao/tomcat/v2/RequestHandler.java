package com.jiangchao.tomcat.v2;

import com.jiangchao.tomcat.http.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class RequestHandler implements Runnable {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
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

            String resp = Response.responseHeader + "OK deer";
            OutputStream outputStream = socket.getOutputStream();
            System.out.println(resp);
            outputStream.write(resp.getBytes());
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (IOException e) {

        } finally {

        }
    }
}
