package com.example.interviewlandbackend.exception;

public class SectionNotFoundException extends RuntimeException{

    public SectionNotFoundException(String message) {
        super(message);
    }
}
