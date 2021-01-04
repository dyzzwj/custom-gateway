package com.dyzwj.customgateway.config;


public interface MyHandler {

    boolean support(String channel);

    void handleRequest(RequestWrapper requestWrapper);

    int order();

}
