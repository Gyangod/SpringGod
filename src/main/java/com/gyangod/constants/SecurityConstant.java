package com.gyangod.constants;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 604_800_000; //7 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GYANGOD_PVT_LTD = "Gyangod Literacy Technologies Pvt. Ltd.";
    public static final String GYANGOD_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to login to view this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/api/user/signup","/api/user/login","/api/user/reset-password"};
}
