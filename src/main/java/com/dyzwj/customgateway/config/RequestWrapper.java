package com.dyzwj.customgateway.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;


public class RequestWrapper extends HttpServletRequestWrapper {
    private String body;

    private HttpServletRequest originalRequest;
    public RequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.originalRequest = request;
    }


    public HttpServletRequest getOriginalRequest(){
        return this.originalRequest;
    }




    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        ServletInputStream servletInputStream = new ServletInputStream() {
            public boolean isFinished() {
                return false;
            }
            public boolean isReady() {
                return false;
            }
            public void setReadListener(ReadListener readListener) {}
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
        return servletInputStream;

    }
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
    public String getBody() {
        return this.body;
    }

    public void setBody(String body){
        this.body = body;
    }

}