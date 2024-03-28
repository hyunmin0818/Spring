package com.codingbox.core3.web.request;

import com.codingbox.core3.web.dto.HelloData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void reqestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream  =request.getInputStream();

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody : " + messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("username : " + helloData.getUsername());
        System.out.println("age : " + helloData.getAge());

        response.getWriter().write("ok");
    }

    /*
    @responsebody : 응답 메세지
     */
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String reqestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {
        HelloData data = objectMapper.readValue(messageBody, HelloData.class);
        System.out.println("username : " + data.getUsername());
        System.out.println("age : " + data.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String reqestBodyJsonV3(@RequestBody HelloData data) throws JsonProcessingException {
        System.out.println("username : " + data.getUsername());
        System.out.println("age : " + data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String reqestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws JsonProcessingException {
        HelloData data = httpEntity.getBody();
        System.out.println("username : " + data.getUsername());
        System.out.println("age : " + data.getAge());
        return "ok";
    }

    /*
    @responseobdy, @requestbody
        -> 내부적으로 httpMessageConverter 객체를 사용

     */
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData reqestBodyJsonV5(@RequestBody HelloData data) throws JsonProcessingException {
        System.out.println("username : " + data.getUsername());
        System.out.println("age : " + data.getAge());
        return data;
    }
}
