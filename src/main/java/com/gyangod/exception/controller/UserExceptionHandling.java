package com.gyangod.exception.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gyangod.exception.domain.*;
import com.gyangod.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class UserExceptionHandling extends ExceptionHandling{
    public static final String ACCOUNT_LOCKED = "Your account has been locked due to security reasons. Please click Forgot Password";
    public static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    public static final String ACCOUNT_EXPIRED = "Your account has expired due to some issues, please verify again and login";
    public static final String INCORRECT_CREDENTIALS = "Username / Password incorrect. Please try again";
    public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    public static final String EMAIL_EXIST = "Email id is registered with us. Please use login";
    public static final String EMAIL_NOT_FOUND = "Email id is not registered with us. Please use a valid one";
    public static final String USER_NAME_EXIST = "User name already exists. Please login or create a new one";
    public static final String USER_NAME_NOT_FOUND = "User name is not found in the system. Please enter a valid one";
    public static final String PASSWORD_DOES_NOT_MATCH = "The password you have entered does not match. You can click on forgot password to generate one";

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST,ACCOUNT_DISABLED);
    }

    @ExceptionHandler(NotAnImageFileException.class)
    public ResponseEntity<HttpResponse> notAnImageFileException(NotAnImageFileException e) {
        return createHttpResponse(UNPROCESSABLE_ENTITY,e.getMessage());
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<HttpResponse> accountExpiredException() {
        return createHttpResponse(SERVICE_UNAVAILABLE,ACCOUNT_EXPIRED);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> accountLockedException() {
        return createHttpResponse(LOCKED,ACCOUNT_LOCKED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(UNAUTHORIZED,NOT_ENOUGH_PERMISSION);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(NOT_ACCEPTABLE,INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException e) {
        return createHttpResponse(FORBIDDEN,e.getMessage());
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<HttpResponse> emailExistException() {
        return createHttpResponse(BAD_REQUEST,EMAIL_EXIST);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException() {
        return createHttpResponse(BAD_REQUEST,EMAIL_NOT_FOUND);
    }

    @ExceptionHandler(UsernameExistException.class)
    public ResponseEntity<HttpResponse> usernameExistException() {
        return createHttpResponse(BAD_REQUEST,USER_NAME_EXIST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<HttpResponse> usernameNotFoundException() {
        return createHttpResponse(BAD_REQUEST,USER_NAME_NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException() {
        return createHttpResponse(BAD_REQUEST,USER_NAME_NOT_FOUND);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<HttpResponse> mediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return createHttpResponse(NOT_ACCEPTABLE,e.getMessage());
    }

}
