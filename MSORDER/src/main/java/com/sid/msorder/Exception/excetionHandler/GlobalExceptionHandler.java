package com.sid.msorder.Exception.excetionHandler;

import com.sid.msorder.Dtos.ErrorBody;
import com.sid.msorder.Exception.OrderNotValidException;
import com.sid.msorder.Exception.ShippingNotFound;
import com.sid.msorder.Exception.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotValidException.class)
    public ResponseEntity<?> orderNotFoundException(OrderNotValidException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(ShippingNotFound.class)
    public ResponseEntity<?> shippingNotFoundException(ShippingNotFound ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<?> handleValidatorException(ValidatorException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE,
                Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }


}
