package com.hardews.jizhang.utils;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class JwtPayloadHolder {
    private static final ThreadLocal<Long> CONTEXT = new ThreadLocal<>();

    public static Long getClaims() {
        return CONTEXT.get();
    }

    public static void setClaims(Long id) {
        CONTEXT.set(id);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
