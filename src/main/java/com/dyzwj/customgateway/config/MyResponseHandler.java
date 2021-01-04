package com.dyzwj.customgateway.config;

import javax.xml.ws.Response;

/**
 * 响应处理handler
 */
public interface MyResponseHandler extends MyHandler{

    /**
     * 处理响应
     * @param responseWrapper
     */
    void handlerResponse(ResponseWrapper responseWrapper);
}
