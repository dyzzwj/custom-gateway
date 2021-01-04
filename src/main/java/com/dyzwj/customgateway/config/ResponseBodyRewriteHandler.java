package com.dyzwj.customgateway.config;

import org.springframework.stereotype.Component;

@Component
public class ResponseBodyRewriteHandler implements MyResponseHandler {

    @Override
    public void handlerResponse(ResponseWrapper responseWrapper) {
        responseWrapper.getBody();
    }

    @Override
    public boolean support(String channel) {
        return false;
    }

    @Override
    public int order() {
        return 0;
    }
}
