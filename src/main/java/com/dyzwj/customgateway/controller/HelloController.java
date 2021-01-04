package com.dyzwj.customgateway.controller;

import com.dyzwj.customgateway.config.RequestWrapper;
import com.dyzwj.customgateway.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/user")
    public String user(HttpServletRequest request,@RequestBody User user){
        System.out.println(request);
        System.out.println("user:"+user);

        HttpHeaders headers = new HttpHeaders();
        //设置请求媒体数据类型
        headers.setContentType(MediaType.APPLICATION_JSON);
        //设置返回媒体数据类型
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        Map<String,Object> json = new HashMap<>();
        json.put("username","zwj");
        json.put("password","123456");

        HttpEntity<User> formEntity = new HttpEntity<>(user, headers);
        return restTemplate.postForObject("http://localhost:4444/hello/user", formEntity, String.class);
    }
}
