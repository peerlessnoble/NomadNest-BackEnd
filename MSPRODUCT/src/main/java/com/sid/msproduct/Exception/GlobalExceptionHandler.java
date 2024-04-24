package com.sid.msproduct.Exception;

import jakarta.validation.ValidationException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(NotFoundException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(EmptyValueException.class)
    public ResponseEntity<?> emptyEntryExceptionHandler(EmptyValueException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        // Return ResponseEntity
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {

        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

}
