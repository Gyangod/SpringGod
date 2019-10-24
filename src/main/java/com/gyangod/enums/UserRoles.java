package com.gyangod.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum UserRoles {

    STUDENT(1),
    STUDENTADMIN(11),
    STUDENTADMINFACILITATOR(111),
    STUDENTFACLITATOR(101),
    TEACHER(2),
    TEACHERADMIN(12),
    TEACHERADMINFACILITATOR(112),
    TEACHERFACLITATOR(102),
    FACILITATOR(100);

    private static final Map<Integer,UserRoles> USER_ROLES_MAP = new HashMap<>();

    static{
        for(UserRoles roles : EnumSet.allOf(UserRoles.class)){
            USER_ROLES_MAP.put(roles.getCode(),roles);
        }
    }

    private int code;

    UserRoles(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserRoles get(int code){
        return USER_ROLES_MAP.get(code);
    }

}
