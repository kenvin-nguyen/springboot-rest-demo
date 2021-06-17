package com.example.demo.domain;

public class ErrorMessage {
    private String message;
    private String requestPath;
    private String port;
    private String requestMethod;

    public ErrorMessage(String message, String requestPath, String port, String requestMethod) {
        this.message = message;
        this.requestPath = requestPath;
        this.port = port;
        this.requestMethod = requestMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
