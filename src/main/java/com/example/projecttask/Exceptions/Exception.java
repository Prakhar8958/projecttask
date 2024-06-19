package com.example.projecttask.Exceptions;

public class Exception extends RuntimeException{
    public Exception() {
        super("Resource Not Found Exception");
    }
    public Exception(String message) {
        super(message);
    }
}
