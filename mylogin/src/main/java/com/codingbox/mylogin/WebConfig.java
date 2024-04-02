package com.codingbox.mylogin;

import com.codingbox.mylogin.filter.LogFilter;
import com.codingbox.mylogin.filter.LoginCheckFilter;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter(){
    FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<Filter>();
    // 내가 만든 LogFilter를 넣어준다.
    filterFilterRegistrationBean.setFilter(new LogFilter());
    filterFilterRegistrationBean.setOrder(1);
    // 모든 url에 다 적용된다.
    filterFilterRegistrationBean.addUrlPatterns("/*");
    return filterFilterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean LoginCheckFilter(){
    FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<Filter>();
    filterFilterRegistrationBean.setFilter(new LoginCheckFilter());
    filterFilterRegistrationBean.setOrder(2);
    filterFilterRegistrationBean.addUrlPatterns("/*");
    return filterFilterRegistrationBean;
    }
}
