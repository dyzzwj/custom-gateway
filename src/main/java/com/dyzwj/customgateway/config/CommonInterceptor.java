package com.dyzwj.customgateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@Component
public class CommonInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

    @Autowired
    List<MyRequestHandler> myRequestHandlers;




    public List<MyRequestHandler> getMyRequestHandlers() {
        return myRequestHandlers;
    }


    public CommonInterceptor() {
    }

    @PostConstruct
    public void init(){
        //对handler排序
       Collections.sort(getMyRequestHandlers(),(h1, h2) -> h1.order() > h2.order() ? 1: -1);

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(request instanceof RequestWrapper){
            String channel = "ali";
            System.out.println(request);
            for (MyRequestHandler myRequestHandler : getMyRequestHandlers()) {
                if (myRequestHandler.support(channel)){
                    myRequestHandler.handleRequest((RequestWrapper) request);
                }
            }
        }
        return true;
    }

    /**
     * 此方法执行之前 reponse body已经响应给客户端了
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("postHandle:"+response);
//        String channel = "ali";
//        if(response instanceof ResponseWrapper){
//            for (MyResponseHandler myResponseHandler : getMyResponseHandlers()) {
//                if(myResponseHandler.support(channel)){
//                    myResponseHandler.handlerResponse((ResponseWrapper) response);
//                }
//            }
//        }

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion:"+response);
        if(response instanceof ResponseWrapper){
            System.out.println(new String(((ResponseWrapper) response).getBody()));
        }

    }




}