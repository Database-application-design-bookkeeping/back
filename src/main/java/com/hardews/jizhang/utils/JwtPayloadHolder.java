package com.hardews.jizhang.utils;

import com.auth0.jwt.interfaces.Claim;

import java.util.Map;

public class JwtPayloadHolder {
    private static final ThreadLocal<Map<String, Claim>> CONTEXT = new ThreadLocal<>();

    public static Map<String, Claim> getClaims() {
        return CONTEXT.get();
    }

    public static void setClaims(Map<String, Claim> claims) {
        CONTEXT.set(claims);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
