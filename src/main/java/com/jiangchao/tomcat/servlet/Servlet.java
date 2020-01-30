package com.jiangchao.tomcat.servlet;

import com.jiangchao.tomcat.http.Request;
import com.jiangchao.tomcat.http.Response;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public interface Servlet {

    void init() throws Exception;

    void service(Request request, Response response) throws Exception;

    void destroy();
}
