package com.gyangod.exception.controller;

import com.gyangod.model.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class PackageExceptionHandling extends ExceptionHandling {

    /*@ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST,ACCOUNT_DISABLED);
    }*/

}
