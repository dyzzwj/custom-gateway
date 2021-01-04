package com.dyzwj.customgateway.config;

public interface MyHandler {

    boolean support(String channel);


    int order();

}
