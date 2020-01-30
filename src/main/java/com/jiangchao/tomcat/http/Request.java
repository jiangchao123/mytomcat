package com.jiangchao.tomcat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public class Request {

    private String uri;

    private String method;

    public Request(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String[] data = reader.readLine().split(" ");
            this.uri = data[1];
            this.method = data[0];
        } catch (Exception e) {

        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
