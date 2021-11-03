package com.self.cloudserver.config;

import com.self.cloudserver.constants.ApiUri;
import com.self.cloudserver.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcCfg implements WebMvcConfigurer {

    private static List<String> excludePath = new ArrayList<>();

    static {
        //excludePath.add("/**/test");
    }

    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登陆拦截
        registry.addInterceptor(loginInterceptor()).addPathPatterns(ApiUri.MODULE_URI_PREFIX + "/*").excludePathPatterns(excludePath).order(1);
    }

}
