package com.gyangod.exception.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gyangod.exception.domain.BlankFieldException;
import com.gyangod.exception.domain.ObjectNotFoundException;
import com.gyangod.exception.domain.RegexMatchException;
import com.gyangod.exception.domain.UnauthorizedException;
import com.gyangod.model.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    protected static final Logger LOGGER = LogManager.getLogger(ExceptionHandling.class);
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing a request. Please report the bug";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String PAGE_NOT_FOUND = "The page you requested is not mapped";
    private static final String MAXFILE_SIZE = "The maximum file size is 2 MB";
    public static final String ERROR_PATH = "/error";
    public static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";

    @ExceptionHandler(RegexMatchException.class)
    public ResponseEntity<HttpResponse> regexMatchException(RegexMatchException e) {
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<HttpResponse> jwtVerificationException(JWTVerificationException e) {
        return createHttpResponse(FORBIDDEN,e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<HttpResponse> customUnauthorizedException(UnauthorizedException e) {
        return createHttpResponse(UNAUTHORIZED,e.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HttpResponse> customUnauthorizedException(MissingServletRequestParameterException e) {
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<HttpResponse> customUnauthorizedException(IllegalArgumentException e) {
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<HttpResponse> objectNotFoundException(ObjectNotFoundException e) {
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<HttpResponse> maximumSizeException(MaxUploadSizeExceededException e) {
        LOGGER.error(e);
        return createHttpResponse(BANDWIDTH_LIMIT_EXCEEDED,MAXFILE_SIZE);
    }

    @ExceptionHandler(BlankFieldException.class)
    public ResponseEntity<HttpResponse> blankFieldException(BlankFieldException e) {
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotAllowedException(HttpRequestMethodNotSupportedException e) {
        HttpMethod supportedMethod = Objects.requireNonNull(e.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED,String.format(METHOD_IS_NOT_ALLOWED,supportedMethod));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResponse> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.error(e);
        return createHttpResponse(BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException e) {
        LOGGER.error(e);
        return createHttpResponse(INTERNAL_SERVER_ERROR,ERROR_PROCESSING_FILE);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> noResultException(NoResultException e) {
        LOGGER.error(e);
        return createHttpResponse(INTERNAL_SERVER_ERROR,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception e) {
        LOGGER.error(e);
        return createHttpResponse(INTERNAL_SERVER_ERROR,INTERNAL_SERVER_ERROR_MSG);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(UNAUTHORIZED,NOT_ENOUGH_PERMISSION);
    }

    protected ResponseEntity<HttpResponse> createHttpResponse(HttpStatus status, String message){
        return new ResponseEntity<>(new HttpResponse(status,message),status);
    }

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, PAGE_NOT_FOUND);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
