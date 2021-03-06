package com.dyzwj.customgateway.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

@Component
public class ResponseBodyRewriteHandler implements MyResponseHandler {

//    @Override
//    public String handlerResponse(ResponseWrapper responseWrapper) {
//        System.out.println("==handlerResponse===");
//        String body = new String(responseWrapper.getBody());
//        Map map = JSON.parseObject(body, Map.class);
//        map.put("xixi","haha");
//        return JSON.toJSONString(map);
//    }


    @Override
    public String handlerResponse(Object body) {
        String s = body.toString();
        Map res = JSON.parseObject(s, Map.class);
        res.put("xixi","haha");
        return JSON.toJSONString(res);
    }

    @Override
    public boolean support(String channel) {
        return true;
    }

    @Override
    public int order() {
        return 0;
    }
}
