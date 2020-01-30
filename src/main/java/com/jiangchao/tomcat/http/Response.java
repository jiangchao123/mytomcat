package com.jiangchao.tomcat.http;

import java.io.OutputStream;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class Response {

    public OutputStream outputStream;

    public static final String responseHeader = "HTTP/1.1 200 \r\n" +
            "Content-Type: text/html \r\n" + "\r\n";

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
