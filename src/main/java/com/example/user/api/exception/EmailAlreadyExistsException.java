package com.example.user.api.exception;

public class EmailAlreadyExistsException extends RuntimeException  {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
