package com.dyzwj.customgateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {
    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

    @Autowired
    List<MyHandler> handlers;

    public CommonInterceptor() {
    }

    @PostConstruct
    public void init(){
        //对handler排序
       Collections.sort(handlers,(h1,h2) -> h1.order() > h2.order() ? 1: -1);

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request instanceof RequestWrapper){
            String channel = "ali";
            System.out.println(request);
            for (MyHandler myHandler : handlers) {
                if (myHandler.support(channel)){
                    myHandler.handleRequest((RequestWrapper) request);
                }
            }
        }
        return true;
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle:"+response);
        if(response instanceof ResponseWrapper){
            System.out.println(new String(((ResponseWrapper) response).getBody()));
        }
        super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion:"+response);
        if(response instanceof ResponseWrapper){
            System.out.println(new String(((ResponseWrapper) response).getBody()));
        }
        super.afterCompletion(request, response, handler, ex);
    }

    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


}