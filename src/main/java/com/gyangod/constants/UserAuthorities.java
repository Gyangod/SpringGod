package com.gyangod.constants;

public class UserAuthorities {
    public static final String[] STUDENT = { "user:read" };
    public static final String[] TEACHER = { "user:read", "pack:create" };
    public static final String[] ADMIN = { "user:read", "user:update" };
    public static final String[] SUPER_ADMIN = { "user:read", "user:update" , "user:delete" };
//    public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update", "user:delete" };
}
