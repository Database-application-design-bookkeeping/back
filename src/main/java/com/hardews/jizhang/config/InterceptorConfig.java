package com.hardews.jizhang.config;

import com.hardews.jizhang.component.Cors;
import com.hardews.jizhang.component.Verify;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器，并指定拦截路径
        registry.addInterceptor(new Verify())
                .addPathPatterns("/**")
                .excludePathPatterns("/jizhang/user/login","/jizhang/user/login/email","/jizhang/user/register", "/jizhang/user/code");

        registry.addInterceptor(new Cors())
                .addPathPatterns("/**");
    }
}
