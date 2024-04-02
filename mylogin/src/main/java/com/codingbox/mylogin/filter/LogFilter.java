package com.codingbox.mylogin.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 올바른 변수 이름을 사용하여 getRequestURI() 호출
        String requestURI = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid: " + uuid);
        System.out.println("requestURI: " + requestURI);
        chain.doFilter(request, response);
    }
}
