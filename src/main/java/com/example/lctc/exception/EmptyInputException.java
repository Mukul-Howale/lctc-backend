package com.example.lctc.exception;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException(String message) {
        super(message);
    }
}
