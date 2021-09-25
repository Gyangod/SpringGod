package com.gyangod.enums.statemachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum UserStatusEvents {
    lock,
    dead,
    activate,
    deactivate;

    public static List<String> getUserEvents(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

}
