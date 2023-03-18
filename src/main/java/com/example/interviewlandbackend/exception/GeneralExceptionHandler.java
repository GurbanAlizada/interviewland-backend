package com.example.interviewlandbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex,
             @NotNull HttpHeaders headers,
             @NotNull HttpStatus status,
             @NotNull WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ContentNotFoundException.class)
    public ResponseEntity<?> handle(ContentNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<?> handle(QuestionNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SectionNotFoundException.class)
    public ResponseEntity<?> handle(SectionNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> handle(ImageNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<?> handle(TokenNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handle(AccessDeniedException exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception exception){
        return new ResponseEntity<>(exception.getMessage() , HttpStatus.NOT_FOUND);
    }




}
