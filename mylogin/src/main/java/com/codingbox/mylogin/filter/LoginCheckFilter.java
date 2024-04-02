package com.codingbox.mylogin.filter;

import com.codingbox.mylogin.session.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginCheckFilter implements Filter {
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        System.out.println("인증 체크 필터 시작: " + requestURI);
        if(isLoginCHeckPath(requestURI)){
            // filter를 태울 예정
            System.out.println("인증 체크 로직 실행: " + requestURI);
            HttpSession session = httpServletRequest.getSession(false);
            if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null ){
                System.out.println("미인증 사용자 요청" + requestURI);
                // 로그인 Redirect
                httpServletResponse.sendRedirect("/login?redirectURL=" + requestURI);
                // 여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝
            }
        }


        // 그대로 진행
        chain.doFilter(request, response);
    }

    // 화이트 리스트의 경우 인증체크 하지 않는다
    private boolean isLoginCHeckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}
