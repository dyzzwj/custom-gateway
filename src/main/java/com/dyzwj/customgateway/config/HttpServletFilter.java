package com.dyzwj.customgateway.config;

import com.alibaba.fastjson.JSON;
import com.dyzwj.customgateway.po.DataBase;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
@WebFilter(urlPatterns = "/**")
public class HttpServletFilter implements Filter {



    public void init(FilterConfig filterConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String way = "ali";
        ServletRequest requestWrapper = null;
        ServletResponse responseWrapper = null;
        System.out.println("我是过滤器");
        if(request instanceof HttpServletRequest) {
//            String uri = ((HttpServletRequest) request).getRequestURI();
            requestWrapper = new RequestWrapper((HttpServletRequest) request);
        }
        if (response instanceof HttpServletResponse){
            responseWrapper = new ResponseWrapper((HttpServletResponse)response);
        }

        if(requestWrapper != null && responseWrapper != null) {
            chain.doFilter(requestWrapper, responseWrapper);
        } else {
            chain.doFilter(request, response);
        }
    }





    public void destroy() {

    }
}
