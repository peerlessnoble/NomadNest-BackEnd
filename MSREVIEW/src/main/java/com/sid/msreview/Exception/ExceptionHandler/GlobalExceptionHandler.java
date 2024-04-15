package com.sid.msreview.Exception.ExceptionHandler;

import com.sid.msreview.Dtos.ErrorBody;
import com.sid.msreview.Exception.ReviewNotFoundException;
import com.sid.msreview.Exception.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> reviewNotFoundException(ReviewNotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<?> reviewNotFoundException(ValidatorException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
}

