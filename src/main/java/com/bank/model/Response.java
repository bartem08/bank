package com.bank.model;

public class Response {

    private String message;

    private String statusCode;

    public Response() {
    }

    public Response(String message, String statusCode) {
        setMessage(message);
        setStatusCode(statusCode);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
