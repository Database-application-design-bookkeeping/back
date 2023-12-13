package com.hardews.jizhang.config;

import com.hardews.jizhang.component.Verify;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        //允许跨域访问资源定义
        registry.addMapping("/**")
                .allowedOrigins("https://bookping.hardews.cn")
                // 允许发送凭证: 前端如果配置改属性为true之后，则必须同步配置
                .allowCredentials(true)
                // 允许所有方法
                .allowedMethods("*")

                .allowedHeaders("*");
    }
}
