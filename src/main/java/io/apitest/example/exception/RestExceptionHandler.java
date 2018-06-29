package io.apitest.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by prasantabiswas on 29/06/18.
 */

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ErrorResponse> exceptionCustomerHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionGeneralHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("The request could not be understood by the server due to malformed syntax.");
        ex.printStackTrace();
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
}
