package com.john.spring_author_practice.common;

import com.john.spring_author_practice.common.dto.ErrorDto;
import com.john.spring_author_practice.common.dto.ResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RuntimeExceptionHandler {

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(false, null, HttpStatus.BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException e) {
        // ToDo : 더 좋은 방법 고민해보고 적용해보기
        //  (ResponseDto 하나에 Obj를 바꿔가며 보내는게 좋을 거 같은데 다른 사람들 어떻게 하나 봐야할듯)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(false, null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(false, null, HttpStatus.BAD_REQUEST.value(), message));
    }
}