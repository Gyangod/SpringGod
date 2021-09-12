package com.gyangod.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class HttpResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-YYYY hh:mm:ss", timezone="Asia/Kolkata")
    private Date timestamp;
    private int statusCode;
    private HttpStatus status;
    private String reason;
    private String message;

    public HttpResponse(HttpStatus status, String message) {
        this.timestamp = new Date();
        this.statusCode = status.value();
        this.status = status;
        this.reason = status.getReasonPhrase().toUpperCase();
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
