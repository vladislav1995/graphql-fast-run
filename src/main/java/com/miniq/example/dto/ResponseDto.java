package com.miniq.example.dto;

import java.util.Date;

/**
 * @author Uladik
 */
public class ResponseDto {

    private Long id;
    private String method;
    private Integer statusCode;
    private String message;
    private Date date;

    public ResponseDto() {
    }

    public ResponseDto(Long id, String method, Integer statusCode, String message, Date date) {
        this.id = id;
        this.method = method;
        this.statusCode = statusCode;
        this.message = message;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
