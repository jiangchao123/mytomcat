package com.jiangchao.tomcat.servlet;

import com.jiangchao.tomcat.http.Request;
import com.jiangchao.tomcat.http.Response;

/**
 * Created by jiangchao08 on 2020/1/30.
 */
public abstract class HttpServlet implements Servlet {

    /**
     * 如果有请求过来，就会调用这个方法
     * 然后再跟进请求类型来调不同的doXXX()
     * @param request
     * @param response
     * @throws Exception
     */
    public void service(Request request, Response response) throws Exception {
        if ("get".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }

    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request, Response response);
}
