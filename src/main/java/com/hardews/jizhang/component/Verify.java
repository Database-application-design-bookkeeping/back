package com.hardews.jizhang.component;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hardews.jizhang.utils.Jwt;
import com.hardews.jizhang.utils.JwtPayloadHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class Verify implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的Token
        String token = request.getHeader("Authorization");

        if (token == null){
            // 没有Token
            response.setStatus(403);
            response.getWriter().write("Missing Token");
            return false;
        }

        try {
            // 验证令牌
            DecodedJWT verify = Jwt.verify(token);
            Map<String, Claim> payload = verify.getClaims();
            // 将负载信息存储在ThreadLocal中
            System.out.println(payload);
            JwtPayloadHolder.setClaims(payload);
            return true;
        } catch (Exception e) {
            // Token验证失败
            response.setStatus(403);
            response.getWriter().write("Invalid Token");
            return false;
        }
    }
}
