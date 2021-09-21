package com.gyangod.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public enum CountryCodes {
    India("+91"),
    Bangladesh("+880"),
    Germany("+49"),
    United_States("+1"),
    United_Kingdom("+44");

    private String code;

    CountryCodes(String code) {
        this.code = code;
    }



    public static Map<String,String> getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).collect(Collectors.toMap(s -> s.name().replace("_"," "), Enum::toString,(oldValue, newValue) -> oldValue, TreeMap::new));
    }

    @Override
    public String toString() {
        return code;
    }
}
