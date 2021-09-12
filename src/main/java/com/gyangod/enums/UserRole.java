package com.gyangod.enums;

import static com.gyangod.constants.UserAuthorities.*;

public enum UserRole {

    ROLE_STUDENT(STUDENT),
    ROLE_TEACHER(TEACHER),
    ROLE_OPERATOR(ADMIN),
//    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_ADMIN(SUPER_ADMIN);

    private String[] authorities;

    UserRole(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
