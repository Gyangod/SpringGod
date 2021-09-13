package com.gyangod.exception.domain;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String object) {
        super(String.format("The request does not have %s object. Please create the object",object));
    }
}
