package com.gyangod.exception.domain;

public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("You are not eligible for it");
    }
}
