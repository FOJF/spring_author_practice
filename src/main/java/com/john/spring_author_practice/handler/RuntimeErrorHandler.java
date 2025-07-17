package com.john.spring_author_practice.handler;

import com.john.spring_author_practice.dto.ErrorDto;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RuntimeErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> entityExistsException(EntityExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }
}