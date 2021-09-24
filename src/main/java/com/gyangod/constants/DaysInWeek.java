package com.gyangod.constants;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum DaysInWeek {
    MON("Monday"),
    TUE("Tuesday"),
    WED("Wednesday"),
    THU("Thursday"),
    FRI("Friday"),
    SAT("Saturday"),
    SUN("Sunday");

    private final String day;

    DaysInWeek(String day) {
        this.day = day;
    }

    public static Map<String,String> getDaysInWeek(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).collect(Collectors.toMap(Enum::toString, Enum::name,(oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    @Override
    public String toString() {
        return day;
    }
}
