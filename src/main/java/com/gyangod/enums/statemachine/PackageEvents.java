package com.gyangod.enums.statemachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PackageEvents {
    activate,
    change,
    deactivate,
    destroy;

    public static List<String> getPackageEvents(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }
}
