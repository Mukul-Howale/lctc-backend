package com.example.lctc.exception;

import com.example.lctc.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedException extends ResponseEntityExceptionHandler {

    // Handles all exception
    @ExceptionHandler(GeneralException.class)
    public final ResponseEntity<Object> handleAllException(Exception e, WebRequest webRequest){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), e.getStackTrace(), webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyInputException.class)
    public final ResponseEntity<Object> handleEmptyInputException(Exception e, WebRequest webRequest){

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), e.getStackTrace(), webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
