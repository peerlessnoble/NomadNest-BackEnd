package com.sid.catalogservice.Exception.exceptionHandler;

import com.sid.catalogservice.Dtos.ErrorBody;
import com.sid.catalogservice.Exception.AlreadyExistException;
import com.sid.catalogservice.Exception.EmptyValueException;
import com.sid.catalogservice.Exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.Date;

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

}
