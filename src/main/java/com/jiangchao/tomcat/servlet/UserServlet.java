package com.jiangchao.tomcat.servlet;

import com.jiangchao.tomcat.http.Request;
import com.jiangchao.tomcat.http.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class UserServlet extends HttpServlet {
    public void init() throws Exception {

    }

    public void destroy() {

    }

    public void doGet(Request request, Response response) {
        this.doPost(request, response);
    }

    public void doPost(Request request, Response response) {
        try {
            OutputStream outputStream = response.outputStream;
            String result = Response.responseHeader + "user handler success!";
            outputStream.write(result.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
