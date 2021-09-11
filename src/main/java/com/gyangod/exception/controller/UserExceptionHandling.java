package com.gyangod.exception.controller;

import com.gyangod.model.HttpResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandling {
    private final Log LOGGER = LogFactory.getLog(UserExceptionHandling.class);
    public static final String ACCOUNT_LOCKED = "Your account has been locked due to security reasons. Please click Forgot Password";
    public static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    public static final String ACCOUNT_EXPIRED = "Your account has expired due to some issues, please verify again and login";
    public static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    public static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing a request. Please report the bug";
    public static final String INCORRECT_CREDENTIALS = "Username / Password incorrect. Please try again";
    public static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(HttpStatus.BAD_REQUEST,ACCOUNT_DISABLED);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus status, String message){
        return new ResponseEntity<>(new HttpResponse(status,message),status);
    }
}
