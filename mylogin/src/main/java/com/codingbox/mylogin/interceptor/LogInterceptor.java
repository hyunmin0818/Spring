package com.codingbox.mylogin.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logid";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        if(handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;
        }
        System.out.println("[interceptor] uuid : " + uuid);
        System.out.println("[interceptor] requestURI : " + requestURI);
        System.out.println("[interceptor] handler : " + handler);

        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("[interceptor] postHandle : " + modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);

        System.out.println("[interceptor logid : " + logId);
        System.out.println("[interceptor requestURI : " + requestURI);
    }
}
