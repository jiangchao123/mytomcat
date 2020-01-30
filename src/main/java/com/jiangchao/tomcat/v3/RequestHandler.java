package com.jiangchao.tomcat.v3;

import com.jiangchao.tomcat.http.Request;
import com.jiangchao.tomcat.http.Response;
import com.jiangchao.tomcat.servlet.HttpServlet;

import java.io.IOException;
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
            Request request = new Request(socket.getInputStream());
            Response response = new Response(socket.getOutputStream());

            String uri = request.getUri();
            String servletName = TomcatServerV3.urlMapping.get(uri);

            HttpServlet httpServlet = TomcatServerV3.servletMapping.get(servletName);
            if (httpServlet != null) {
                httpServlet.service(request, response);
            } else {
                String resp = Response.responseHeader + "can not fount servlet";
                OutputStream outputStream = socket.getOutputStream();
                System.out.println(resp);
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
