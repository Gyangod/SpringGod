package com.gyangod.exception.domain;

public class BlankFieldException extends Exception{
    private static final String AUTO_GENERATED_MESSAGE = "Please give %s, it cannot be blank";
    public BlankFieldException(String fieldName) {
        super(String.format(AUTO_GENERATED_MESSAGE,fieldName));
    }
}
