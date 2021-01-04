package com.dyzwj.customgateway.config;

import javax.xml.ws.Response;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 响应处理handler
 */
public interface MyResponseHandler extends MyHandler{

    /**
     * 处理响应
     * @param body
     */
    String handlerResponse(Object body);


//    String handlerResponse(ResponseWrapper responseWrapper);
}
